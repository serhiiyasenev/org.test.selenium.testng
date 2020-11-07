import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CssDinnerPlay {
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "src\\main\\java\\geckodriver.exe");

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        driver = new FirefoxDriver(options);

        driver.get("https://flukeout.github.io/");
        driver.manage().window().setSize(new Dimension(1024, 768));
    }

    @AfterClass
    public void tearDown(){
        System.out.println("tearDown");
        driver.quit();
    }

    @Test
    public void letsPlay(){
        System.out.println("driver title: " + driver.getTitle());
        System.out.println("==================================");

        // define elements
        WebElement gameLevel = driver.findElement(By.className("level-text"));
        WebElement currentGameTask = driver.findElement(By.className("order"));
        WebElement answerField = driver.findElement(By.xpath("//*[@class='input-strobe' and @type='text']"));
        WebElement enterButton = driver.findElement(By.xpath("//div[@contains(@class, 'enter-button') and contains(text(), 'enter')]"));


        // define explicit waits
        WebDriverWait waitBefore = new WebDriverWait(driver, 8);
        WebDriverWait waitAfter = new WebDriverWait(driver, 5);
    }
}
