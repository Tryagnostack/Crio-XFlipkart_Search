package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void SearchWrapper(String text, WebDriver driver){
        WebElement search = driver.findElement(By.className("Pke_EE"));
        search.sendKeys(text);
        Actions act = new Actions(driver);
        act.sendKeys(Keys.ENTER).perform();
    }

    public static String ScrollandPrint(WebDriver driver, WebElement ele){
        Actions act = new Actions(driver);

        act.scrollToElement(ele).perform();
        String rate = ele.getText();
        return rate;
    }

    public static void initTest(WebDriver driver){
        driver.get("https://www.flipkart.com");
    }
}
