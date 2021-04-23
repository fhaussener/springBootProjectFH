package ch.fhnw.webec.controller;

import ch.fhnw.webec.model.Rating;
import ch.fhnw.webec.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PlaceController {

    private final PlaceService service;

    @Autowired
    public PlaceController(PlaceService service) {
        this.service = service;
    }

    // Gets add form for rating and adds city and place to model
    @GetMapping("/rating")
    public ModelAndView getRating(@RequestParam Long placeId, @RequestParam String city) {
        final Map<String, Object> model = new HashMap<>();
        service.findById(placeId).ifPresent(place -> model.put("place", place));
        model.put("city", city);
        return new ModelAndView("add", model);
    }

    // Adds rating to a existing place and redirects to modified city
    @PostMapping("/rating")
    public String addRating(@ModelAttribute Rating newRating, @RequestParam long placeId, @RequestParam String city) {
        service.addRatingToPlace(placeId, newRating);

        return "redirect:/?city=" + city;
    }

    // Deletes place by given ids
    @DeleteMapping("/places/{id}")
    public String deletePlace(@PathVariable Long id) {
        service.deleteById(id);
        return "index";
    }
}
