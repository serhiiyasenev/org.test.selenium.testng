import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPageSearch {

    private WebDriver driver;

    public MainPageSearch(WebDriver driver){
        this.driver = driver;
    }

    By searchFiled = By.xpath("//input[@name='search']");
    By searchButton = By.xpath("//button[@class='button button_color_green button_size_medium search-form__submit']");
    By firstItem = By.xpath("//span[@class='goods-tile__title']");
    By catalogHeader = By.xpath("//h1[@class='catalog-heading']");
    By pageHeader = By.xpath("//div[@class='central-wrapper']//div[@class='layout']/div");


    public MainPageSearch searchFieldInput(String search){
        driver.findElement(searchFiled).sendKeys(search);
        return this;
    }

    public MainPageSearch searchButtonClick(){
        driver.findElement(searchButton).click();
        return this;
    }

    public MainPageSearch waitForPageTitle(String title){
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(driver.findElement(catalogHeader), title));
        return this;
    }

    public MainPageSearch checkTheFirstItemIs(String expectedText){
        String actualText = driver.findElement(firstItem).getText();
        Assert.assertTrue(actualText.contains(expectedText));
        return this;
    }

    public MainPageSearch checkTheAbsenceOfItem(){
        Assert.assertTrue(driver.findElement(firstItem).isDisplayed());
        return this;
    }

    public MainPageSearch checkFindItemText(String expectedText){
        String actualText = driver.findElement(pageHeader).getText();
        Assert.assertTrue(actualText.contains(expectedText));
        return this;
    }
}
