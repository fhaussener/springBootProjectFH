package ch.fhnw.webec.service;

import ch.fhnw.webec.dao.PlaceRepository;
import ch.fhnw.webec.model.Place;
import ch.fhnw.webec.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class PlaceService {

    private final PlaceRepository repository;

    @Autowired
    public PlaceService(PlaceRepository repository) {
        this.repository = repository;
    }

    public Optional<Place> findById(long id) {
        return repository.findById(id);
    }

    // Finds place and adds rating to it's rating list
    // Returns modified place
    public Place addRatingToPlace(long placeId, Rating newRating) {
        Optional<Place> optionalPlace = repository.findById(placeId);
        Place foundPlace = optionalPlace.get();
        foundPlace.getRatings().add(newRating);
        return repository.save(foundPlace);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    // Gets average rating of place for each rating type
    // Returns Map of type and average ratings
    public Map<String, Integer> getAverageRatings(long placeId) {
        Map<String, Integer> averages = new HashMap<>();
        double avgCoffeeRating = 0;
        double avgPowerPlugRating = 0;
        double avgInternetRating = 0;

        Optional<Place> optionalPlace = repository.findById(placeId);
        Place foundPlace = optionalPlace.get();
        int ratingSize = foundPlace.getRatings().size();

        // Go through all ratings and add them up to total number
        for (Rating rating : foundPlace.getRatings()) {
            avgCoffeeRating += rating.getCoffee();
            avgPowerPlugRating += rating.getPowerPlug();
            avgInternetRating += rating.getInternet();
        }

        // Get average and floor it + cast to type int
        averages.put("coffee", (int) Math.floor(avgCoffeeRating / ratingSize));
        averages.put("powerPlug", (int) Math.floor(avgPowerPlugRating / ratingSize));
        averages.put("internet", (int) Math.floor(avgInternetRating / ratingSize));

        return averages;
    }
}
