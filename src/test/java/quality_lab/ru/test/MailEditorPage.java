package quality_lab.ru.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class MailEditorPage {
    private WebDriver driver;

    public MailEditorPage(WebDriver driver) {
        this.driver = driver;
    }

    public SuccessSendedMailPage sendMessage(String messageTo, String messageSubject, String messageBody) {
        By bySendMessageTo = By.xpath("//textarea[@data-original-name='To']");
        TestHelper.waitForVisibleElement(driver, bySendMessageTo);
        WebElement sendMessageTo = driver.findElement(bySendMessageTo);

        sendMessageTo.sendKeys(messageTo);

        WebElement subjectElement = driver.findElement(By.xpath("//div[@data-label='Subject']//input[@name='Subject']"));
        subjectElement.sendKeys(messageSubject);

        driver.switchTo().frame(driver.findElement(By.xpath("//div[contains(@class , 'compose__editor')]//iframe")));
        WebElement sendMessageBody = driver.findElement(By.xpath("//body[@id='tinymce']"));
        sendMessageBody.click();
        sendMessageBody.sendKeys(messageBody);

        driver.switchTo().defaultContent();
        WebElement sendMessageButton = driver.findElement(By.xpath("//div[@data-name='send']/span[@class='b-toolbar__btn__text']"));
        sendMessageButton.click();

        TestHelper.waitForVisibleElement(driver, By.xpath("//div[@id='b-compose__sent']//div[contains(@class, 'message-sent__title')]"));

        return new SuccessSendedMailPage(driver);
    }

}
