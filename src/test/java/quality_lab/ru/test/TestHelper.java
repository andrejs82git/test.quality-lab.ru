package quality_lab.ru.test;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class TestHelper {
    static void waitForPageLoad(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(wdriver, 60);

        Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

            public boolean apply(WebDriver input) {
                return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
            }

        };
        wait.until(pageLoaded);
    }

    static void waitForVisibleElement(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);  // you can reuse this one
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static void assertIsNotEmpty(String str) {
         assertTrue(isNotEmpty(str));
    }
}
