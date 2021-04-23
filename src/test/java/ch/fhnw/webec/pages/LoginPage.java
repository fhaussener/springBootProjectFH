package ch.fhnw.webec.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    /*EXTERNAL (inspired from school example) : https://github.com/WebEngineering-FHNW/contact-list-security-netopyr/blob/master/src/test/java/ch/fhnw/webec/contactlistsecurity/pages/LoginPage.java */
    private static final String URL = "http://localhost:%d/login";

    public static LoginPage to(WebDriver driver, int port) {
        driver.get(String.format(URL, port));
        return PageFactory.initElements(driver, LoginPage.class);
    }

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(tagName = "button")
    private WebElement button;

    @FindBy(className = "logout")
    private WebElement logout;

    public void login(String usernameValue, String passwordValue) {
        username.clear();
        username.sendKeys(usernameValue);
        password.clear();
        password.sendKeys(passwordValue);
        button.click();
    }

    public WebElement getLogout() {
        return logout;
    }
}
