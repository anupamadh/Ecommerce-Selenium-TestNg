package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class ResultsPage extends BasePage {
    private WebDriver driver;
    private JavascriptExecutor js;
    private By resultsList = By.xpath("//li[contains(@class,'ast-col-sm-12')]");
    private String URL = "s=";
    private By pricesList = By.xpath("//li[contains(@class,'ast-col-sm-12')]//span[contains(@class,'woocommerce-Price-amount amount')]");

    public ResultsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL);
    }

    public int productsCount(){
        List<WebElement> productsList = driver.findElements(resultsList);
        return productsList.size();
    }

    public boolean verifySearchResult() {
        boolean result = false;
        if (productsCount() > 0) {
            result = true;
        }
        result = isOpen() && result;
        return result;
    }

    public void printPrices(){
        List<WebElement> productsList = driver.findElements(pricesList);
        Iterator it = productsList.iterator();
        ArrayList<Double> prices = new ArrayList<>();
        for (WebElement e: productsList){
            prices.add(Double.parseDouble(e.getText().substring(1)));
        }
        Collections.sort(prices, Collections.reverseOrder());
        System.out.println(prices);
    }

    public ProductPage clickOnProduct(int index){
        List<WebElement> productsList = driver.findElements(resultsList);
        WebElement product = productsList.get(index-1);
        product.click();
        return new ProductPage(driver);
    }

}
