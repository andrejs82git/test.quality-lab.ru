package quality_lab.ru.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class InboxPage {
    public static final By MAIL_EDITOR_BUTTON = By.xpath("//div[@class='b-toolbar__item']//span");
    public static final By MAIL_TO_TEXTAREA = By.xpath("//textarea[@data-original-name='To']");
    private WebDriver driver;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
    }

    public MailEditorPage getMailEditorPage(){
        WebElement mailEditorButton = driver.findElement(MAIL_EDITOR_BUTTON);
        mailEditorButton.click();

        TestHelper.waitForVisibleElement(driver, MAIL_TO_TEXTAREA);
        return new MailEditorPage(driver);
    }
}
