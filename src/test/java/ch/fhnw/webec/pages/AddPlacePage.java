package ch.fhnw.webec.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddPlacePage {
    private static final String URL = "http://localhost:%d/add";


    public static AddPlacePage to(WebDriver driver, int port) {
        driver.get(String.format(URL, port));
        return PageFactory.initElements(driver, AddPlacePage.class);
    }

    private final WebDriver driver;

    public AddPlacePage(WebDriver driver) {
        this.driver = driver;
    }


    @FindBy(className = "add-form")
    private List<WebElement> addForm;

    @FindBy(id = "placeName")
    private WebElement placeName;

    @FindBy(id = "pictureUrl")
    private WebElement pictureUrl;

    @FindBy(tagName = "button")
    private WebElement button;

    @FindBy(id = "cancel")
    private WebElement cancel;

    public List<WebElement> getAddForm() {
        return addForm;
    }

    public WebElement getPlaceName() {
        return placeName;
    }

    public WebElement getPictureUrl() {
        return pictureUrl;
    }

    public WebElement getButton() {
        return button;
    }

    public WebElement getCancel() {
        return cancel;
    }
}
