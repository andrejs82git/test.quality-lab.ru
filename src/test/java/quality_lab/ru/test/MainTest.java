package quality_lab.ru.test;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.Assert.*;

/**
 * Created by andrejs on 02.10.2017.
 */
public class MainTest {
    private WebDriver driver;

    @org.junit.Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        driver.close();
    }

    @org.junit.Test
    public void main() throws Exception {
        System.out.println("Hello !!!!!");
        driver.get("http://mail.ru");
        waitForPageLoad(driver);
        WebElement ph_authLink = driver.findElement(By.id("PH_authLink"));
        ph_authLink.click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'account.mail.ru/')]")));

        By byLogin = By.xpath("//span[@class='b-email__name']/input[@name='Username']");
        waitForVisibleElement(driver, byLogin);

        WebElement login = driver.findElement(byLogin);
        WebElement password = driver.findElement(By.xpath("//div[@data-field-name='Password']//input[@name='Password']"));
        WebElement loginBuuton = driver.findElement(By.xpath("//button[@data-uniqid='toolkit-1']"));

        login.sendKeys("andrej.s.82@list.ru");
        password.sendKeys("TriTraTwo123");
        loginBuuton.click();

        waitForPageLoad(driver);

        WebElement openSendMessagePage = driver.findElement(By.xpath("//div[@class='b-toolbar__item']//span"));
        openSendMessagePage.click();

        By bySendMessageTo = By.xpath("//textarea[@data-original-name='To']");
        waitForVisibleElement(driver, bySendMessageTo);
        WebElement sendMessageTo = driver.findElement(bySendMessageTo);

        sendMessageTo.sendKeys("andrejs82@gmail.com");

        WebElement subjectElement = driver.findElement(By.xpath("//div[@data-label='Subject']//input[@name='Subject']"));
        subjectElement.sendKeys("Hello world!");

        driver.switchTo().frame(driver.findElement(By.xpath("//div[contains(@class , 'compose__editor')]//iframe")));
        WebElement sendMessageBody = driver.findElement(By.xpath("//body[@id='tinymce']"));
        sendMessageBody.click();
        sendMessageBody.sendKeys("BlaBlaBla");

        driver.switchTo().defaultContent();
        WebElement sendMessageButton = driver.findElement(By.xpath("//div[@data-name='send']/span[@class='b-toolbar__btn__text']"));
        sendMessageButton.click();

        waitForVisibleElement(driver, By.xpath("//div[@id='b-compose__sent']//div[contains(@class, 'message-sent__title')]"));

    }

    private static void waitForPageLoad(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(wdriver, 60);

        Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

            public boolean apply(WebDriver input) {
                return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
            }

        };
        wait.until(pageLoaded);
    }

    private static void waitForVisibleElement(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);  // you can reuse this one
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
