import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLoginAndLogout {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\java\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/login");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
       driver.quit();
    }

    @Test(priority = 1, description = "check opened login page")
    public void Login(){
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test(priority = 2, description = "login and check elements")
    public void LoginElement(){
        WebElement loginElement = driver.findElement(By.id("txtUsername"));
        WebElement passwordElement = driver.findElement(By.xpath("//input[@name='txtPassword']"));
        WebElement submitElement = driver.findElement(By.xpath("//input[@type='submit']"));

        loginElement.sendKeys("Admin");
        passwordElement.sendKeys("admin123");
        submitElement.click();

        WebElement marketPlaceMark = driver.findElement(By.id("MP_link"));
        Assert.assertEquals(marketPlaceMark.getAttribute("value"), "Marketplace");

        driver.navigate().back();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        driver.navigate().refresh();
        WebElement warningMessage = driver.findElement(By.xpath("//div[@class='message warning']"));
        Assert.assertEquals(warningMessage.getText(), "Invalid Request");
    }
}
