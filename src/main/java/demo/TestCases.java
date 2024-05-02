package demo;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public static void GoTOCalculator(WebDriver driver){
        driver.get("https://www.google.com");
        
        driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys("calculator");
        driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']/center/input[@value='Google Search']")).click();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        
        GoTOCalculator(driver);
        if(driver.getCurrentUrl().contains("Google")){
        
        

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-section']")));


        if (driver.findElement(By.xpath("//div[@class='card-section']")).isDisplayed()) {
            System.out.println("Verify that the Google Calculator loads.");
            if (driver.findElement(By.xpath("//span[@id='cwos']")).isDisplayed())
                System.out.println("Confirm that the initial display shows zero(0).");
             else 
                System.out.println("Google Calculator is NOT Verified");
        }else
            System.out.println("Element is Not Found");
        }else{
            System.out.println("Your are not Current Google Page");
        }

        System.out.println("end Test case: testCase01");
    }

    public static void numberandSignSelect(WebDriver driver,String num) throws InterruptedException{

        driver.findElement(By.xpath("//div[text()='"+num+"']")).click();
        Thread.sleep(2000);
    }


    public void testCase02() throws InterruptedException{

        System.out.println("Start Trest Case 02");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        GoTOCalculator(driver);
        numberandSignSelect(driver, "5");
        numberandSignSelect(driver, "+");
        numberandSignSelect(driver, "7");
        numberandSignSelect(driver, "=");

        WebElement Result = driver.findElement(By.xpath("//span[@id='cwos']"));
        wait.until(ExpectedConditions.visibilityOf(Result));
        System.out.println("Number is Dispalayed :: "+Result.getText().equals("12"));

        System.out.println("End TestCase 02");
        
    }

    public void testCase03() throws InterruptedException{
        System.out.println("Start Trest Case 03");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        GoTOCalculator(driver);
        numberandSignSelect(driver, "3");
        driver.findElement(By.xpath("//div[@aria-label='multiply']")).click();
        numberandSignSelect(driver, "1");
        numberandSignSelect(driver, "0");
        numberandSignSelect(driver, "=");
        WebElement Result = driver.findElement(By.xpath("//span[@id='cwos']"));
        wait.until(ExpectedConditions.visibilityOf(Result));
        System.out.println("Confirm that the displayed result is correct (in this case, 30).  :: "+Result.getText().equals("30"));
        numberandSignSelect(driver, "AC");
        wait.until(ExpectedConditions.visibilityOf(Result));
        System.out.println("Verify that the display clears  :: "+Result.getText().equals("0"));
        System.out.println("End TestCase 03");
    }

    public void testCase04() throws InterruptedException{
        System.out.println("Start Trest Case 04");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        GoTOCalculator(driver);
        numberandSignSelect(driver, "2");
        numberandSignSelect(driver, "0");
        driver.findElement(By.xpath("//div[@aria-label='divide']")).click();
        numberandSignSelect(driver, "5");
        numberandSignSelect(driver, "=");
        WebElement Result = driver.findElement(By.xpath("//span[@id='cwos']"));
        wait.until(ExpectedConditions.visibilityOf(Result));
        System.out.println("Confirm that the displayed result is correct (in this case, 5). :: "+Result.getText().equals("4"));
        System.out.println("End TestCase 04");

    }

}
