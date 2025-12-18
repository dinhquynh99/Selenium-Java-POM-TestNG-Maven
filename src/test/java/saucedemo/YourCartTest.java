package saucedemo;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.saucedemo.ProductsListPO;
import pageObjects.saucedemo.YourCartPO;

public class YourCartTest extends BaseTest {
    private WebDriver driver;
    private ProductsListPO productsListPage;
    private YourCartPO yourCartPage;

    @Parameters({"Browser", "Url"})
    @BeforeMethod
    public void beforeMethod(String browserName, String Url) {
        driver = getBrowserDriver(browserName, Url);

        productsListPage = loginAsValidUser();
        productsListPage.addItemToCartByIndex(0);
        productsListPage.addItemToCartByIndex(1);
        productsListPage.addItemToCartByIndex(2);

        yourCartPage = productsListPage.clickCartIcon();

    }

    @Test
    public void TC_O1_Verify_Header_Displayed() {
        Assert.assertTrue(yourCartPage.isHeaderLablelDisplay());
        Assert.assertEquals(yourCartPage.getCartItems().size(), 3);
    }

    @Test
    public void TC_02_Remote_Product_In_Your_Cart(){
        yourCartPage.remoteItemByIndex(0);

        Assert.assertEquals(yourCartPage.getCartItems().size(),2);
    }

    @Test
    public void TC_03_Click_Continue_Shopping_Navigate_To_Products_Page(){
        ProductsListPO productsListPage = yourCartPage.clickIconContinueShopping();

        Assert.assertTrue(productsListPage.isHeaderLabelDisplay());
        Assert.assertTrue(productsListPage.getCurrentUrl().contains("inventory.html"));

    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();

    }

}

