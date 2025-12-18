package pageObjects.saucedemo;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucedemo.YourCartUI;

import java.util.List;

public class YourCartPO extends BasePage {
    private WebDriver driver;

    public YourCartPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHeaderLablelDisplay(){
        waitElementVisible(driver, YourCartUI.HEADER_LABEL);
        return isElementDisplayed(driver, YourCartUI.HEADER_LABEL);
    }

    public List<WebElement> getCartItems(){
        return getListElement(driver, YourCartUI.CART_ITEMS);
    }

//    public boolean isCartLisDisplay(){
//        return getCartItems().size()>0;
//    }

    public void remoteItemByIndex(int index){
        getListElement(driver, YourCartUI.REMOVE_BUTTON).get(index).click();
    }

    public ProductsListPO clickIconContinueShopping(){
        clickToElement(driver, YourCartUI.CONTINUE_SHOPPING_BUTTON);
        return new ProductsListPO(driver);
    }
}
