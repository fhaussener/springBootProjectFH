package ch.fhnw.webec;

import ch.fhnw.webec.model.City;
import ch.fhnw.webec.model.Place;
import ch.fhnw.webec.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToObject;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class CityControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @Test
    void selectCityShouldReturnCorrectData() throws Exception {
        // given
        final City city = new City();
        final Place place = new Place();
        final Rating rating = new Rating();
        city.setKey("luzern");
        city.setName("Luzern");
        place.setPlaceName("Kafi");
        place.setPictureUrl("picture");
        place.getRatings().add(rating);
        rating.setCoffee(30);
        rating.setPowerPlug(30);
        rating.setInternet(30);
        city.getPlaces().add(place);

        entityManager.persist(city);
        entityManager.flush();

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/?city=" + city.getKey())
                        .with(
                                SecurityMockMvcRequestPostProcessors.user("user").roles("USER")
                        )
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Wähle eine Stadt aus")))
                .andExpect(model().attribute("selected", equalToObject(city)));
    }

    @Test
    void addShouldReturnCorrectUrl() throws Exception {
        // given
        final City city = new City();

        entityManager.persist(city);
        entityManager.flush();

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/add")
                        .with(
                                SecurityMockMvcRequestPostProcessors.user("user").roles("USER")
                        )
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Neues Café erfassen")));
    }

    @Test
    void addPlaceToCityShouldAddCorrectData() throws Exception {
        // given
        final City city = new City();
        city.setKey("bern");
        city.setName("Bern");

        entityManager.persist(city);
        entityManager.flush();

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/")
                        .contentType("application/x-www-form-urlencoded")
                        .content("key=" + city.getKey() + "&placeName=test&pictureUrl=https%3A%2F%2Fimages.unsplash.com%2Fphoto&coffee=53&powerPlug=74&internet=48")
                        .with(
                                SecurityMockMvcRequestPostProcessors.user("user").roles("USER")
                        )
        );

        // then
        result.andExpect(status().isFound())
                .andExpect(redirectedUrl("/?city=" + city.getKey()));
    }

    @Test
    void addPlaceWithinvalidUrlShouldNotAddData() throws Exception {
        // given
        final City city = new City();
        city.setKey("bern");
        city.setName("Bern");

        entityManager.persist(city);
        entityManager.flush();

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/")
                        .contentType("application/x-www-form-urlencoded")
                        .content("key=" + city.getKey() + "&placeName=test&pictureUrl=nolink&coffee=53&powerPlug=74&internet=48")
                        .with(
                                SecurityMockMvcRequestPostProcessors.user("user").roles("USER")
                        )
        );

        // then
        result.andExpect(status().isBadRequest()).andExpect(redirectedUrl(null));
    }
}
