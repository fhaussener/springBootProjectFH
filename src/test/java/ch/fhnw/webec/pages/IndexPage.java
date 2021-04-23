package ch.fhnw.webec.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IndexPage {
    private static final String URL = "http://localhost:%d/";

    public static IndexPage to(WebDriver driver, int port) {
        driver.get(String.format(URL, port));
        return PageFactory.initElements(driver, IndexPage.class);
    }

    private final WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(className = "place-container")
    private List<WebElement> cityCards;

    @FindBy(className = "no-selection")
    private List<WebElement> noSelectMessage;

    @FindBy(className = "selector-link")
    private List<WebElement> selectorLink;

    @FindBy(className = "logout-link")
    private List<WebElement> logoutLink;

    @FindBy(className = "add-rating-link")
    private List<WebElement> addRatingLink;

    @FindBy(className = "title-place")
    private List<WebElement> titlePlace;

    @FindBy(className = "rating-place")
    private List<WebElement> ratingPlace;

    @FindBy(className = "delete-link")
    private List<WebElement> deleteLink;

    @FindBy(id = "new-place")
    private WebElement newPlace;

    public List<WebElement> getCityCards() {
        return cityCards;
    }

    public List<WebElement> getNoSelectMessage() {
        return noSelectMessage;
    }

    public List<WebElement> getSelectorLink() {
        return selectorLink;
    }

    public List<WebElement> getLogoutLink() {
        return logoutLink;
    }

    public List<WebElement> getAddRatingLink() {
        return addRatingLink;
    }

    public List<WebElement> getTitlePlace() {
        return titlePlace;
    }

    public WebElement getNewPlace() {
        return newPlace;
    }

    public List<WebElement> getRatingPlace() {
        return ratingPlace;
    }

    public List<WebElement> getDeleteLink() {
        return deleteLink;
    }
}


