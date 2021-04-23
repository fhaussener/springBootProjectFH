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


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class LoginControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginShouldReturnCorrectData() throws Exception {
        // given
        String loginUrlError = "/login";

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get(loginUrlError)
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Login")));
    }

    @Test
    void loginShouldReturnCorrectDataOnError() throws Exception {
        // given

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/login").param("error", "")
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Die Anmeldung ist fehlgeschlagen.")));
    }

    @Test
    void loginShouldReturnCorrectDataOnLogout() throws Exception {
        // given

        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/login").param("logout", "")
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string(containsString("Sie wurden erfolgreich abgemeldet.")));
    }
}
