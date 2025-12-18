package saucedemo;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.saucedemo.LoginPO;
import pageObjects.saucedemo.ProductsListPO;

import java.util.List;

public class ProductListTest extends BaseTest {
    private WebDriver driver;
    private LoginPO loginPage;
    private ProductsListPO productsListPage;

    @Parameters({"Browser", "Url"})
    @BeforeClass
    public void beforeClass(String browserName, String Url) {
        driver = getBrowserDriver(browserName, Url);

        productsListPage = loginAsValidUser();;
    }

    @Test
    public void TC_01_Verify_Header_Displayed(){
        Assert.assertTrue(productsListPage.isHeaderLabelDisplay());

    }

    @Test
    public void TC_02_Verify_Product_List_Displayed(){
        Assert.assertEquals(productsListPage.getNumberOfProducts(),6);

    }

    @Test
    public void TC_03_Verify_Sort_Options_By_Selecting_Each() {
        List<String> expectedOptions = List.of(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)"
        );

        // verify tất cả option tồn tại
        Assert.assertEquals(productsListPage.getAllSortOptionTexts(), expectedOptions);
        productsListPage.sleepInSecond(2);

        // verify select từng option
        for (String option : expectedOptions) {
            productsListPage.selectSortByVisibleText(option);
            Assert.assertEquals(productsListPage.getSelectedSortText(), option,
                    "Selected option text is incorrect");
            productsListPage.sleepInSecond(2);
        }
    }

    @Test
    public void TC_04_Add_To_Cart() {
        productsListPage.addItemToCartByIndex(0);
        Assert.assertEquals(productsListPage.getCartCount(), 1);
        productsListPage.sleepInSecond(2);

        productsListPage.addItemToCartByIndex(1);
        Assert.assertEquals(productsListPage.getCartCount(), 2);
        productsListPage.sleepInSecond(2);

    }

    @Test
    public void TC_05_Remove_Out_Cart(){
        productsListPage.removeItemToCartByIndex(0);
        Assert.assertEquals(productsListPage.getCartCount(),1);

        // Vì remote index 0 thì List button chỉ còn 1 phần tử nên vẫn là index 0
        productsListPage.removeItemToCartByIndex(0);
        Assert.assertEquals(productsListPage.getCartCount(),0);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();

    }

}
