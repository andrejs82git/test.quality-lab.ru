package quality_lab.ru.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class InboxPage {
    private WebDriver driver;

    public InboxPage(WebDriver driver) {

        this.driver = driver;
    }

    public MailEditorPage getMailEditorPage(){
        WebElement openSendMessagePage = driver.findElement(By.xpath("//div[@class='b-toolbar__item']//span"));
        openSendMessagePage.click();

        By bySendMessageTo = By.xpath("//textarea[@data-original-name='To']");
        TestHelper.waitForVisibleElement(driver, bySendMessageTo);
        return new MailEditorPage(driver);
    }
}
