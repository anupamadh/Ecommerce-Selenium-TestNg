package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    private WebDriver driver;
    private JavascriptExecutor js;
    private By subTotalDisplay =By.xpath("//tr[@class='cart-subtotal']//span[contains(@class,'woocommerce-Price-amount amount')]");

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public Double verifySubTotal(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(subTotalDisplay));
        String actualSubtotal = driver.findElement(subTotalDisplay).getText().substring(1);
        Double subtotal = Double.parseDouble(actualSubtotal);
        return subtotal;
    }
}
