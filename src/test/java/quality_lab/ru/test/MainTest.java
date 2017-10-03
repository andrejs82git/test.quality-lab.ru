package quality_lab.ru.test;

import org.junit.Test;
import static org.junit.Assert.*;
import static quality_lab.ru.test.TestHelper.assertIsNotEmpty;
import static quality_lab.ru.test.TestHelper.isNotEmpty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by andrejs on 02.10.2017.
 */
public class MainTest {
    private static final String LOGIN = "mail.ru.login";
    private static final String PASSWORD = "mail.ru.password";
    private static final String MESSAGE_TO = "messageTo";
    private static final String MESSAGE_SUBJECT = "messageSubject";
    private static final String MESSAGE_BODY = "messageBody";
    private WebDriver driver;

    @org.junit.Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        driver.close();
    }

    @Test
    public void variableTest() throws Exception {
        assertIsNotEmpty(System.getProperty(LOGIN));
        assertIsNotEmpty(System.getProperty(PASSWORD));

        assertIsNotEmpty(System.getProperty(MESSAGE_TO));
        assertIsNotEmpty(System.getProperty(MESSAGE_SUBJECT));
        assertIsNotEmpty(System.getProperty(MESSAGE_BODY));
    }

    @Test
    public void mainTest() throws Exception {
        IndexPage indexPage = new IndexPage(driver);

        String login = System.getProperty(LOGIN);
        String password = System.getProperty(PASSWORD);
        InboxPage inboxPage = indexPage.getLoginPage().loginAndGetInboxPage(login, password);

        String messageTo = System.getProperty(MESSAGE_TO);
        String messageSubject = System.getProperty(MESSAGE_SUBJECT);
        String messageBody = System.getProperty(MESSAGE_BODY);

        MailEditorPage mailEditorPage = inboxPage.getMailEditorPage();
        SuccessSendedMailPage successSendedMailPage = mailEditorPage.sendMessage(messageTo, messageSubject, messageBody);
    }

}
