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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class PlaceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @Test
    void selectRatingShouldReturnCorrectData() throws Exception {
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
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/rating?placeId=" + place.getId() + "&city=" + city.getKey())
                        .with(
                                SecurityMockMvcRequestPostProcessors.user("user").roles("USER")
                        )
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Neues Rating erfassen")))
                .andExpect(model().attribute("place", equalToObject(place)))
                .andExpect(model().attribute("city", containsString("zuerich")));
    }

    @Test
    void postRatingShouldAddCorrectData() throws Exception {
        // given
        final City city = new City();
        final Place place = new Place();
        final Rating rating = new Rating();
        city.setKey("zuerich");
        city.setName("Zürich");
        place.setPlaceName("Kafi");
        rating.setCoffee(30);
        rating.setPowerPlug(30);
        rating.setInternet(30);

        city.getPlaces().add(place);

        entityManager.persist(city);
        entityManager.flush();

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/rating?placeId=" + place.getId() + "&city=" + city.getKey())
                        .contentType("application/x-www-form-urlencoded")
                        .content("coffee=60&powerPlug=39&internet=45")
                        .with(
                                SecurityMockMvcRequestPostProcessors.user("user").roles("USER")
                        )
        );

        // then
        result.andExpect(status().isFound())
                .andExpect(redirectedUrl("/?city=" + city.getKey()));
    }

    @Test
    void deletePlaceShouldDeletePlace() throws Exception {
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
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.delete("/places/"+place.getId())
                        .with(
                                SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN")
                        )
        );

        // then
        result.andExpect(status().isOk());
    }
}
