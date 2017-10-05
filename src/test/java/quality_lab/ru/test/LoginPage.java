package quality_lab.ru.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class LoginPage {
    public static final By LOGIN_INPUT = By.xpath("//span[@class='b-email__name']/input[@name='Username']");
    public static final By PASSWORD_INPUT = By.xpath("//div[@data-field-name='Password']//input[@name='Password']");
    public static final By LOGIN_BUTTON = By.xpath("//button[@data-uniqid='toolkit-1']");
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public InboxPage loginAndGetInboxPage(String login, String password){
        WebElement loginElem = driver.findElement(LOGIN_INPUT);
        WebElement passwordElem = driver.findElement(PASSWORD_INPUT);
        WebElement loginButton = driver.findElement(LOGIN_BUTTON);

        loginElem.sendKeys(login);
        passwordElem.sendKeys(password);
        loginButton.click();

        TestHelper.waitForPageLoad(driver);
        return new InboxPage(driver);
    }

}
