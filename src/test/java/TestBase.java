import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    WebDriver dr;

    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void init() {
        dr = new ChromeDriver();
        dr.get("https://jere237.softr.app/");
        dr.manage().window().maximize();
        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        dr.quit();
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
