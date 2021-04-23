package ch.fhnw.webec;

import ch.fhnw.webec.dao.CityRepository;
import ch.fhnw.webec.model.City;
import ch.fhnw.webec.model.Place;
import ch.fhnw.webec.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import java.util.Optional;

@DataJpaTest
public class CityRepositoryIT {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findFirstByKeyShouldReturnCity() {
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
        final Optional<City> result = cityRepository.findFirstByKey(city.getKey());

        // then
        assertThat(result).hasValue(city);
    }
}
