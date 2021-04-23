package ch.fhnw.webec;

import ch.fhnw.webec.model.City;
import ch.fhnw.webec.model.Place;
import ch.fhnw.webec.model.Rating;
import ch.fhnw.webec.service.PlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PlaceServiceIT {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlaceService placeService;

    @Test
    void findPlaceByIdShouldReturnCorrectData() {
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
        final Optional<Place> optionalPlace = placeService.findById(place.getId());
        Place result = optionalPlace.get();

        // then
        assertThat(result.getPlaceName()).isEqualTo(place.getPlaceName());
    }

    /*
    @Test
    void deletePlaceByIdShouldDeletePlace() {
        // given
        final City city = new City();
        final Place place = new Place();
        city.setKey("zuerich");
        city.setName("Zürich");
        place.setPlaceName("Kafi");
        city.getPlaces().add(place);

        entityManager.persist(city);
        entityManager.flush();

        // when
        placeService.deleteById(place.getId());

        // then
        assertThat(city.getPlaces().contains(place)).isEqualTo(false);
        assertThat(place.getPlaceName()).isEqualTo(place.getPlaceName());
    }
     */

    @Test
    void addRatingToPlaceShouldAddCorrectRating() {
        // given
        final City city = new City();
        final Place place = new Place();

        city.setKey("zuerich");
        city.setName("Zürich");
        place.setPlaceName("Kafi");
        city.getPlaces().add(place);

        entityManager.persist(city);
        entityManager.flush();

        final Rating rating = new Rating();
        rating.setCoffee(30);
        rating.setPowerPlug(31);
        rating.setInternet(32);

        // when
        final Place result = placeService.addRatingToPlace(place.getId(), rating);

        // then
        assertThat(result.getRatings().size()).isEqualTo(1);
        assertThat(result.getRatings().get(0).getCoffee()).isEqualTo(30);
        assertThat(result.getRatings().get(0).getPowerPlug()).isEqualTo(31);
        assertThat(result.getRatings().get(0).getInternet()).isEqualTo(32);
    }

    @Test
    void getAverageRatingShouldReturnCorrectData() {
        // given
        final City city = new City();
        final Place place = new Place();
        final Rating rating1 = new Rating();
        final Rating rating2 = new Rating();
        city.setKey("zuerich");
        city.setName("Zürich");
        place.setPlaceName("Kafi");
        rating1.setCoffee(30);
        rating1.setPowerPlug(30);
        rating1.setInternet(30);
        rating2.setCoffee(40);
        rating2.setPowerPlug(40);
        rating2.setInternet(40);

        place.getRatings().add(rating1);
        place.getRatings().add(rating2);

        city.getPlaces().add(place);

        entityManager.persist(city);
        entityManager.flush();

        // when
        final Map<String, Integer> resultRatings = placeService.getAverageRatings(place.getId());

        // then
        assertThat(resultRatings.get("coffee")).isEqualTo(35);
        assertThat(resultRatings.get("powerPlug")).isEqualTo(35);
        assertThat(resultRatings.get("internet")).isEqualTo(35);
    }

}
