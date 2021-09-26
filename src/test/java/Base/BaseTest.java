package Base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;


public class BaseTest {
    protected static WebDriver driver;
    protected NavigationPage nav;
    protected HomePage homePage;
    protected ResultsPage result;
    protected ProductPage productPage;
    protected CartPage cartPage;

    @BeforeClass
    public void launchApplication(){
        Properties props = System.getProperties();
        try{
            props.load(new FileInputStream(new File("resources/test.properties")));
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }

        driver = new ChromeDriver();
        driver.get(System.getProperty("app.url"));
        driver.manage().window().maximize();
        nav = new NavigationPage(driver);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
