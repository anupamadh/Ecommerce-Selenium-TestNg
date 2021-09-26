package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class NavigationPage extends BasePage {

    private WebDriver driver;
    private JavascriptExecutor js;
    private By homeLink = By.xpath("//a[contains(text(),'Home')]");
    private By cartLink = By.xpath("//a[@class='cart-container']");

    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public HomePage home(){
        driver.findElement(homeLink).click();
        System.out.println("Clicked on home");
        return new HomePage(driver);
    }

    public void cart(){
        driver.findElement(cartLink);
        System.out.println("Clicked on cart");
    }
}
