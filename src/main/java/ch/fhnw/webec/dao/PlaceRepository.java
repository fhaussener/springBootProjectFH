package ch.fhnw.webec.dao;

import ch.fhnw.webec.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}

