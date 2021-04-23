package ch.fhnw.webec.service;

import ch.fhnw.webec.dao.CityRepository;
import ch.fhnw.webec.model.City;
import ch.fhnw.webec.model.Place;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private static final String JSON_FILE = "cities.json";

    private final CityRepository repository;
    private final ObjectMapper mapper;


    @Autowired
    public CityService(CityRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() throws IOException {
        mapper.readValue(getClass().getClassLoader().getResource("data/cities.json"), new TypeReference<List<City>>() {
        })
                .forEach(city -> {
                    city.setId(0L);
                    repository.save(city);
                });
    }


    // Finds the city by the key provided
    public Optional<City> findByKey(String key) {
        return repository.findFirstByKey(key);
    }

    // Finds city by cityKey and adds place to places list
    // Returns modified city
    public City addPlaceToCity(Place newPlace, String cityName) {
        Optional<City> optionalCity = repository.findFirstByKey(cityName);
        City foundCity = optionalCity.get();
        foundCity.getPlaces().add(newPlace);
        return repository.save(foundCity);
    }
}
