package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
    private WebDriver driver;
    private JavascriptExecutor js;
    private By addToCartButton = By.xpath("//button[@name='add-to-cart']");
    private By viewCartButton = By.xpath("//a[contains(@title,'View your shopping cart')]");
    private By priceDisplayed = By.xpath("//p[@class='price']//span[contains(@class,'woocommerce-Price-amount amount')]");

    public ProductPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void addToCart(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        WebElement element = driver.findElement(addToCartButton);
        element.click();
    }

    public Double getProductPrice(){
        String price = driver.findElement(priceDisplayed).getText().substring(1);
        Double subtotal = Double.parseDouble(price);
        return subtotal;
    }
    public CartPage ViewCart(){
        driver.findElement(viewCartButton).click();
        return new CartPage(driver);
    }

}
