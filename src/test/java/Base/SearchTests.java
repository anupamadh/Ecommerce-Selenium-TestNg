package Base;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest {
    double expectedSubtotal =0;

    @Test(priority = 1, dataProvider = "shoppingData")
    public void searchVerification(String product, int index){
        homePage = nav.home();
        Assert.assertTrue(homePage.checkLogo());
        result = homePage.search(product);
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
        result.printPrices();
        productPage = result.clickOnProduct(index);
        expectedSubtotal = expectedSubtotal + productPage.getProductPrice();
        System.out.println("sum= " + expectedSubtotal);
        productPage.addToCart();
        try{
            Thread.sleep(4000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void subtotalVerification(){
        cartPage = productPage.ViewCart();
        Double actualSubtotal = cartPage.verifySubTotal();
        Assert.assertEquals(cartPage.verifySubTotal().doubleValue(), expectedSubtotal);
    }

    @DataProvider(name="shoppingData")
    public Object[][] getData() {
        Object data[][] = {{"Chair",5},{"Table",3}};
        return data;
    }



}
