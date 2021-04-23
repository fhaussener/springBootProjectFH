package ch.fhnw.webec;

import ch.fhnw.webec.pages.AddPlacePage;
import ch.fhnw.webec.pages.AddRatingPage;
import ch.fhnw.webec.pages.IndexPage;
import ch.fhnw.webec.pages.LoginPage;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexIT {

    @LocalServerPort
    private int port;

    private final WebDriver driver = new HtmlUnitDriver();

    @BeforeEach
    void login() {
        final LoginPage page = LoginPage.to(driver, port);
        page.login("user", "user");
    }

    @Test
    void initialPageShouldShowNotSelectedState() {
        // when
        final IndexPage page = IndexPage.to(driver, port);

        // then
        assertThat(page.getCityCards()).isEmpty();
        assertThat(page.getNoSelectMessage()).isNotEmpty();
    }

    @Test
    void clickOnCityShouldShowSelectedCity() {
        // when
        final IndexPage page = IndexPage.to(driver, port);

        page.getSelectorLink().get(0).click();

        // then
        assertThat(page.getCityCards()).isNotEmpty();
        assertThat(page.getNoSelectMessage()).isEmpty();
    }

    @Test
    void clickOnLogoutShouldLogout() {
        // when
        final IndexPage page = IndexPage.to(driver, port);
        page.getLogoutLink().get(0).click();
        final LoginPage loginPage = LoginPage.to(driver, port);

        // then
        assertThat(loginPage.getLogout()).isNotNull();
    }

    @Test
    void clickOnNewRatingShouldRedirectToAddPage() {
        // when
        final IndexPage page = IndexPage.to(driver, port);
        page.getSelectorLink().get(0).click();
        page.getAddRatingLink().get(0).click();

        final AddRatingPage ratingPage = AddRatingPage.to(driver, port);
        // then
        assertThat(ratingPage.getRatingTitle()).isNotEmpty();
        assertThat(ratingPage.getRangeContainer()).isNotEmpty();
    }

    @Test
    void createNewRatingShouldCreateNewRating() {
        // when
        final AddRatingPage page = AddRatingPage.to(driver, port);
        page.getButton().click();

        final IndexPage indexPage = IndexPage.to(driver, port);
        // then

        indexPage.getSelectorLink().get(0).click();
        assertThat(indexPage.getRatingPlace().get(0).getText()).isEqualTo("2 Ratings");
        assertThat(indexPage.getCityCards()).isNotEmpty();
        assertThat(indexPage.getSelectorLink()).isNotEmpty();
    }

    @Test
    void clickOnNewPlaceShouldRedirectToAddPage() {
        // when
        final IndexPage page = IndexPage.to(driver, port);

        page.getNewPlace().click();

        final AddPlacePage addPage = AddPlacePage.to(driver, port);
        // then
        assertThat(addPage.getAddForm()).isNotEmpty();
        assertThat(page.getNoSelectMessage()).isEmpty();
    }

    @Test
    void createNewPlaceShouldCreateNewEntry() {
        // when
        final AddPlacePage page = AddPlacePage.to(driver, port);

        page.getPlaceName().sendKeys("PlaceName");
        page.getPictureUrl().sendKeys("http://fdef");
        page.getButton().click();

        final IndexPage indexPage = IndexPage.to(driver, port);
        // then
        indexPage.getSelectorLink().get(0).click();
        assertThat(indexPage.getTitlePlace().get(3).getText()).isEqualTo("PlaceName");
        assertThat(indexPage.getCityCards()).hasSize(4);
        assertThat(indexPage.getSelectorLink()).isNotEmpty();
    }

    @Test
    void cancelNewEntryShouldGoBackToIndex() {
        // when
        final AddPlacePage page = AddPlacePage.to(driver, port);

        page.getCancel().click();

        // then
        final IndexPage indexPage = IndexPage.to(driver, port);
        assertThat(indexPage.getSelectorLink()).isNotEmpty();
    }

    @Test
    void clickOnDeleteShouldDeleteEntry() throws InterruptedException {
        // given
        /*EXTERNAL : https://stackoverflow.com/questions/7926246/why-doesnt-htmlunitdriver-execute-javascript/10810612#10810612*/
        HtmlUnitDriver jsDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
        jsDriver.setJavascriptEnabled(true);
        jsDriver.get(String.format("http://localhost:%d/", port));
        // when
        final LoginPage page = LoginPage.to(jsDriver, port);
        page.login("admin", "admin");

        final IndexPage indexPage = IndexPage.to(jsDriver, port);
        indexPage.getSelectorLink().get(0).click();
        assertThat(indexPage.getCityCards()).hasSize(4);
        indexPage.getDeleteLink().get(0).click();
        Thread.sleep(3000);
        indexPage.getSelectorLink().get(0).click();

        // then
        assertThat(indexPage.getCityCards()).hasSize(3);
    }

}
