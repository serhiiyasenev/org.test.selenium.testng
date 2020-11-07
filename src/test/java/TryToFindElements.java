import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class TryToFindElements {
    private WebDriver driver;

    @BeforeTest
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);

        driver.get("https://rozetka.com.ua");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(description = "get page element and check, match, extract")
    public void findElements(){

        String mainPageSource = driver.getPageSource();

        // 1. check the certain text exists on the page
        Assert.assertTrue(mainPageSource.contains("Доставка по всей Украине"));

        // 2. check equality of expected and actual element (text)
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='menu-toggler__text']")).getText(), "Каталог товаров");

        // 3. check is element enabled on page
        WebElement searchField = driver.findElement(By.xpath("//input[@class='search-form__input ng-untouched ng-pristine ng-valid']"));
        Assert.assertTrue(searchField.isEnabled());

        // 4. check attribute value
        Assert.assertEquals(searchField.getAttribute("placeholder"), "Я ищу...");

        // 5. find list of menu categories links and check count 17
        int count = driver.findElements(By.xpath("//a[@class='menu-categories__link']")).size();
        Assert.assertEquals(count, 17);

    }
}
