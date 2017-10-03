package quality_lab.ru.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class IndexPage {
    private WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage(){
        driver.get("http://mail.ru");
        TestHelper.waitForPageLoad(driver);
        WebElement ph_authLink = driver.findElement(By.id("PH_authLink"));
        ph_authLink.click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'account.mail.ru/')]")));

        By byLogin = By.xpath("//span[@class='b-email__name']/input[@name='Username']");
        TestHelper.waitForVisibleElement(driver, byLogin);
        return new LoginPage(driver);
    }

}
