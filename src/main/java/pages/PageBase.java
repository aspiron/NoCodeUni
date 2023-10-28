package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase {

    public WebDriver dr;

    public PageBase(WebDriver dr) {
        this.dr = dr;
        PageFactory.initElements(dr, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }


    public void clickWithJSExecutor(WebElement element, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) dr;
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        click(element);
    }

    public void typeWithJSExecutor(WebElement element, int x, int y, String text) {
        if (text != null) {
            clickWithJSExecutor(element, x, y);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void goDown(Keys keyboardBtn){
        new Actions(dr).keyDown(keyboardBtn).perform();
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
