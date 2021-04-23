package ch.fhnw.webec.dao;

import ch.fhnw.webec.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
   Optional<City> findFirstByKey(String key);
}

