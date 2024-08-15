package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;


public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test (enabled = true)
    public void testCase01() throws InterruptedException{
        Wrappers.initTest(driver);
        // WebElement search = driver.findElement(By.className("Pke_EE"));
        // System.out.println(search);
        Wrappers.SearchWrapper("Washing Machine",driver);

        WebElement popularity = driver.findElement(By.xpath("//*[@class='zg-M3Z'][text()='Popularity']"));
        popularity.click();

        Thread.sleep(500);

        List<WebElement> ratings = driver.findElements(By.xpath("//*[@class='XQDdHH']"));
        for(WebElement rating : ratings){
            String val = Wrappers.ScrollandPrint(driver, rating);
            if(Float.parseFloat(val) <= 4)
                System.out.println(val);
        }
        // Thread.sleep(1000);
    }

    @Test(enabled = true)
    public void testCase02() throws InterruptedException{
        Wrappers.initTest(driver);
        Wrappers.SearchWrapper("iPhone", driver);

        List<WebElement> discounts = driver.findElements(By.xpath("//*[@class='UkUFwK']"));
        for(WebElement discount : discounts){
            String text = Wrappers.ScrollandPrint(driver, discount);
            String val = text.substring(0,text.length()-5);
            if(Integer.parseInt(val) > 17){
                WebElement ele = discount.findElement(By.xpath("./ancestor::div[contains(@class,'BfVC2z')]/preceding-sibling::*/div[1]"));
                System.out.println(ele.getText());
                System.out.println(text);
            }
        }

        // Thread.sleep(1000);
    }

    @Test (enabled = true)
    public void testCase03() throws InterruptedException{
        Wrappers.initTest(driver);

        Wrappers.SearchWrapper("Coffee Mug", driver);
        driver.findElement(By.xpath("//*[@class='_6i1qKy'][contains(text(),'4')]")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10L)).ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("_6tw8ju")));
        
        List<WebElement> cards = driver.findElements(By.xpath("//div[@class='slAVV4' and not(descendant::div[@class='xgS27m'])]"));
        for(int i = 0; i<5; i++){
            WebElement card = cards.get(i);
            String title = card.findElement(By.xpath("./a[@class='wjcEIp']")).getText();
            String image_URL = card.findElement(By.xpath("./a[@class='VJA3rP']")).getAttribute("href");
            System.out.println(title);
            System.out.println(image_URL);
            System.out.println("------------");
        }
        
        Thread.sleep(1000);
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}