package ch.fhnw.webec.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddRatingPage {
    private static final String URL = "http://localhost:%d/rating?placeId=14&city=bern";


    public static AddRatingPage to(WebDriver driver, int port) {
        driver.get(String.format(URL, port));
        return PageFactory.initElements(driver, AddRatingPage.class);
    }

    private final WebDriver driver;

    public AddRatingPage(WebDriver driver) {
        this.driver = driver;
    }



    @FindBy(className = "rating-title")
    private List<WebElement> ratingTitle;

    @FindBy(className = "range-container")
    private List<WebElement> rangeContainer;

    @FindBy(tagName = "button")
    private WebElement button;


    public List<WebElement> getRatingTitle() {
        return ratingTitle;
    }

    public List<WebElement> getRangeContainer() {
        return rangeContainer;
    }


    public WebElement getButton() {
        return button;
    }
}
