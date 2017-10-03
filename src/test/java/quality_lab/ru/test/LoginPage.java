package quality_lab.ru.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public InboxPage loginAndGetInboxPage(String login, String password){
        By byLogin = By.xpath("//span[@class='b-email__name']/input[@name='Username']");
        WebElement loginElem = driver.findElement(byLogin);
        WebElement passwordElem = driver.findElement(By.xpath("//div[@data-field-name='Password']//input[@name='Password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@data-uniqid='toolkit-1']"));

        loginElem.sendKeys(login);
        passwordElem.sendKeys(password);
        loginButton.click();

        TestHelper.waitForPageLoad(driver);
        return new InboxPage(driver);
    }

}
