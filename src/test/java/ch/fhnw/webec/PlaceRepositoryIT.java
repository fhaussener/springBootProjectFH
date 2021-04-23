package ch.fhnw.webec;

import ch.fhnw.webec.dao.PlaceRepository;
import ch.fhnw.webec.model.City;
import ch.fhnw.webec.model.Place;
import ch.fhnw.webec.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlaceRepositoryIT {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    void findByIdShouldReturnPlace() {
        // given
        final City city = new City();
        final Place place = new Place();
        final Rating rating = new Rating();
        city.setKey("zuerich");
        city.setName("Zürich");
        place.setPlaceName("Kafi");
        place.getRatings().add(rating);
        rating.setCoffee(30);
        rating.setPowerPlug(30);
        rating.setInternet(30);
        city.getPlaces().add(place);

        entityManager.persist(city);
        entityManager.flush();

        // when
        final Optional<Place> result = placeRepository.findById(place.getId());

        // then
        assertThat(result).hasValue(place);
    }

    /*
    @Test
    void deleteByShouldDeletePlace() {
        // given
        final City city = new City();
        final Place place = new Place();
        final Rating rating = new Rating();
        city.setKey("zuerich");
        city.setName("Zürich");
        place.setPlaceName("Kafi");
        place.getRatings().add(rating);
        city.getPlaces().add(place);

        entityManager.persist(city);
        entityManager.flush();

        // when
       placeRepository.deleteById(place.getId());

        // then
        assertThat(city.getPlaces().size()).isEqualTo(0);
    }
     */
}
