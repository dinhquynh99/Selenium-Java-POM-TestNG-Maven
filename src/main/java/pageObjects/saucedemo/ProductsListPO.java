package pageObjects.saucedemo;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageUIs.saucedemo.ProductsListUI;
import pageUIs.saucedemo.YourCartUI;

import java.util.List;

public class ProductsListPO extends BasePage {
    private WebDriver driver;

    public ProductsListPO(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl(){
        return getPageURL(driver );
    }

    public void clickToHamburgerMenu() {
        waitElementClickable(driver, ProductsListUI.HAMBURGER_MENU);
        clickToElement(driver, ProductsListUI.HAMBURGER_MENU);
    }

    public void clickToLogoutButton() {
        waitElementClickable(driver, ProductsListUI.LOGOUT_BUTTON);
        clickToElement(driver, ProductsListUI.LOGOUT_BUTTON);
    }

    public boolean isHeaderLabelDisplay() {
        waitElementVisible(driver, ProductsListUI.HEADER_LABEL);
        return isElementDisplayed(driver, ProductsListUI.HEADER_LABEL);
    }

    public int getNumberOfProducts() {
        waitElementVisible(driver, ProductsListUI.PRODUCTS_ITEMS);
        return getListElementSize(driver, ProductsListUI.PRODUCTS_ITEMS);
    }

    private Select getSortSelect() {
        waitElementVisible(driver, ProductsListUI.SORT_DROPDOWN);
        return new Select(getElement(driver, ProductsListUI.SORT_DROPDOWN));
    }

    public void selectSortByVisibleText(String text) {
        getSortSelect().selectByVisibleText(text);
    }

    public String getSelectedSortText() {
        return getSortSelect().getFirstSelectedOption().getText();
    }

    public List<String> getAllSortOptionTexts() {
        return getSortSelect()
                .getOptions()
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickToAddToCartButton() {
        waitElementClickable(driver, ProductsListUI.ADD_TO_CART_BUTTON);
        clickToElement(driver, ProductsListUI.ADD_TO_CART_BUTTON);
    }

    public void addItemToCartByIndex(int index) {
        getListElement(driver, ProductsListUI.ADD_TO_CART_BUTTON).get(index).click();
    }

    public void addItemToCartByName(String productName) {
        String locator = String.format(
                ProductsListUI.ADD_TO_CART_BY_NAME,
                productName
        );
        waitElementClickable(driver, locator);
        clickToElement(driver, locator);
    }

    public int getCartCount() {
        if (getListElement(driver, ProductsListUI.CART_BADGE).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(
                getElement(driver, ProductsListUI.CART_BADGE).getText()
        );
    }

    public void removeItemToCartByIndex(int index){
        getListElement(driver, ProductsListUI.REMOVE_BUTTON).get(index).click();
    }

    public YourCartPO clickCartIcon() {
        clickToElement(driver, ProductsListUI.CART_ICON);
        return new YourCartPO(driver);
    }

}
