package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private WebDriver driver;
    private JavascriptExecutor js;
    private By searchField = By.id("woocommerce-product-search-field-0");
    private By searchButton = By.xpath("//button[contains(text(),'Search')]");
    private By logoImage = By.xpath("//img[@class='custom-logo']");

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public boolean checkLogo(){
        WebElement image = driver.findElement(logoImage);
        /*
        Javascript checks if the image is has completed loading and the natural width is not undefined and
        the natural width is > 0
         */
        boolean ImagePresent = (boolean)js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",image);
        if (!ImagePresent) {
            System.out.println("Image not displayed.");
        } else {
            System.out.println("Image displayed.");
        }
        return ImagePresent;
    }
    public ResultsPage search(String searchText){
        js.executeScript("window.scrollBy(0,350)", "");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until( ExpectedConditions.visibilityOfElementLocated(searchField));
        driver.findElement(searchField).sendKeys(searchText);
        driver.findElement(searchButton).click();
        return new ResultsPage(driver);

    }
}
