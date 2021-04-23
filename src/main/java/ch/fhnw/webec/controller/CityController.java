package ch.fhnw.webec.controller;

import ch.fhnw.webec.model.City;
import ch.fhnw.webec.model.Place;
import ch.fhnw.webec.model.Rating;
import ch.fhnw.webec.service.CityService;
import ch.fhnw.webec.service.PlaceService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CityController {

    private final CityService service;
    private final PlaceService placeService;

    @Autowired
    public CityController(CityService service, PlaceService placeService) {
        this.service = service;
        this.placeService = placeService;
    }

    // Gets the the places and city-info for the currently selected city key
    // Returns list of places with the calculated average rating and isAdmin status
    @GetMapping
    public ModelAndView getIndex(Authentication authentication, @RequestParam(required = false) String city) {
        final boolean isAdmin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);

        final Map<String, Object> model = new HashMap<>();
        model.put("isAdmin", isAdmin);

        if (Strings.isNotBlank(city)) {
            Optional<City> foundCity = service.findByKey(city);
            if (foundCity.isPresent()) {
                City cityPresent = foundCity.get();
                List<Place> placeList = cityPresent.getPlaces();

                // Calculates average Rating for each Place by types
                for (Place p : placeList) {
                    Map<String, Integer> averages = placeService.getAverageRatings(p.getId());

                    p.setAvgCoffee(averages.get("coffee"));
                    p.setAvgPowerPlug(averages.get("powerPlug"));
                    p.setAvgInternet(averages.get("internet"));
                }
                model.put("selected", cityPresent);
            }
        }
        return new ModelAndView("index", model);
    }

    @GetMapping("/add")
    public String getAdd() {
        return "add";
    }

    // Adds rating to place and adds place to city
    // Redirects to modified city
    @PostMapping
    public ModelAndView addPlaceToCity(@ModelAttribute Place newPlace, @ModelAttribute City selectedCity,
                                 @ModelAttribute Rating newRating) {
        ModelAndView model = new ModelAndView("index");

        // Validate if pictureUrl is in form of link
        String url = newPlace.getPictureUrl();
        Pattern pattern = Pattern.compile("https://.*|http://.*");
        Matcher matcher = pattern.matcher(url);

        if (!matcher.matches()) {
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }

        String cityName = selectedCity.getKey();
        newPlace.getRatings().add(newRating);
        service.addPlaceToCity(newPlace, cityName);

        model.setViewName("redirect:/?city=" + cityName);
        return model;
    }
}
