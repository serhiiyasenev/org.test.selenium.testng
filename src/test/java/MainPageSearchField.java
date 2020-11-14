import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainPageSearchField {
    WebDriver driver;
    MainPageSearch mainSearch;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\java\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.get("https://rozetka.com.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainSearch = new MainPageSearch(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1, description = "check positive search case on main page")
    public void findValidValue(){
        mainSearch.searchFieldInput("пылесос").searchButtonClick().waitForPageTitle("Пылесосы");
        mainSearch.checkFindItemText("Пылесосы");
        mainSearch.checkTheFirstItemIs("Пылесос");
    }

    @Test(priority = 2, description = "check negative search case on main page")
    public void findInvalidValue(){
        mainSearch.searchFieldInput("пылесос").searchButtonClick().waitForPageTitle("<$#!~`>");
        mainSearch.checkTheAbsenceOfItem();
    }
}
