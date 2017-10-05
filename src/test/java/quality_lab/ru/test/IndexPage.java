package quality_lab.ru.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class IndexPage {
    public static final String MAIL_RU_URL = "http://mail.ru";
    public static final By LOGIN_LINK = By.id("PH_authLink");
    public static final By LOGIN_IFRAME = By.xpath("//iframe[contains(@src, 'account.mail.ru/')]");
    public static final By USERNAME_INPUT = By.xpath("//span[@class='b-email__name']/input[@name='Username']");
    private WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage(){
        driver.get(MAIL_RU_URL);
        TestHelper.waitForPageLoad(driver);
        WebElement ph_authLink = driver.findElement(LOGIN_LINK);
        ph_authLink.click();

        driver.switchTo().frame(driver.findElement(LOGIN_IFRAME));

        TestHelper.waitForVisibleElement(driver, USERNAME_INPUT);
        return new LoginPage(driver);
    }


}
