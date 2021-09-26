package Base;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

public class SearchTests extends BaseTest {
    Double expectedSubtotal = new Double(710.00);

    @Test(priority = 1, dataProvider = "shoppingData")
    public void searchVerification(String product, int index){
        homePage = nav.home();
        Assert.assertTrue(homePage.checkLogo());
        result = homePage.search(product);
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
        result.printPrices();
        productPage = result.clickOnProduct(index);
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
        Assert.assertEquals(cartPage.verifySubTotal(), expectedSubtotal);
    }

    @DataProvider(name="shoppingData")
    public Object[][] getData() {
        Object data[][] = {{"Chair",5},{"Table",3}};
        return data;
    }

}
