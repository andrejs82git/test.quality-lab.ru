package quality_lab.ru.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class MailEditorPage {
    public static final By MAIL_TO_TEXTAREA = By.xpath("//textarea[@data-original-name='To']");
    public static final By MAIL_SUBJECT_INPUT = By.xpath("//div[@data-label='Subject']//input[@name='Subject']");
    public static final By BODY_IFRAME = By.xpath("//div[contains(@class , 'compose__editor')]//iframe");
    public static final By BODYEDITOR_INPUT = By.xpath("//body[@id='tinymce']");
    public static final By SEND_MAIL_BUTTON = By.xpath("//div[@data-name='send']/span[@class='b-toolbar__btn__text']");
    public static final By SUCCESS_PAGE_TITLE = By.xpath("//div[@id='b-compose__sent']//div[contains(@class, 'message-sent__title')]");
    private WebDriver driver;

    public MailEditorPage(WebDriver driver) {
        this.driver = driver;
    }

    public SuccessSendedMailPage sendMessage(String messageTo, String messageSubject, String messageBody) {
        TestHelper.waitForVisibleElement(driver, MAIL_TO_TEXTAREA);
        WebElement sendMessageTo = driver.findElement(MAIL_TO_TEXTAREA);

        sendMessageTo.sendKeys(messageTo);

        WebElement subjectElement = driver.findElement(MAIL_SUBJECT_INPUT);
        subjectElement.sendKeys(messageSubject);

        driver.switchTo().frame(driver.findElement(BODY_IFRAME));
        WebElement sendMessageBody = driver.findElement(BODYEDITOR_INPUT);
        sendMessageBody.click();
        sendMessageBody.sendKeys(messageBody);

        driver.switchTo().defaultContent();
        WebElement sendMessageButton = driver.findElement(SEND_MAIL_BUTTON);
        sendMessageButton.click();

        TestHelper.waitForVisibleElement(driver, SUCCESS_PAGE_TITLE);

        return new SuccessSendedMailPage(driver);
    }

}
