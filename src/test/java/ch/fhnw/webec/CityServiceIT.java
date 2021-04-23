package ch.fhnw.webec;

import ch.fhnw.webec.model.City;
import ch.fhnw.webec.model.Place;
import ch.fhnw.webec.model.Rating;
import ch.fhnw.webec.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CityServiceIT {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CityService cityService;

    @Test
    void findCityByKeyShouldReturnCorrectData() {
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
        final Optional<City> optionalResult = cityService.findByKey(city.getKey());
        City result = optionalResult.get();

        // then
        assertThat(result.getName()).isEqualTo(city.getName());
    }

    @Test
    void addPlaceToCityShouldReturnCorrectData() {
        // given
        final City city = new City();
        final Place place1 = new Place();
        final Place place2 = new Place();
        final Rating rating = new Rating();
        city.setKey("luzern");
        city.setName("Luzern");
        place1.setPlaceName("Kafi");
        place1.getRatings().add(rating);
        rating.setCoffee(30);
        rating.setPowerPlug(30);
        rating.setInternet(30);
        city.getPlaces().add(place1);

        entityManager.persist(city);
        entityManager.flush();

        // when
        final City result = cityService.addPlaceToCity(place2, city.getKey());

        // then
        assertThat(result.getName()).isEqualTo(city.getName());
        assertThat(result.getPlaces()).isEqualTo(city.getPlaces());
        assertThat(result.getPlaces().size()).isEqualTo(2);
    }
}
