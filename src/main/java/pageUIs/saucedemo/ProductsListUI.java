package pageUIs.saucedemo;

public class ProductsListUI {
    public static final String HAMBURGER_MENU = "//button[@id='react-burger-menu-btn']";
    public static final String LOGOUT_BUTTON = "//a[@id='logout_sidebar_link']";
    public static final String HEADER_LABEL = "//span[text()='Products']";
    public static final String PRODUCTS_ITEMS = "//div[@class='inventory_item']";
    public static final String SORT_DROPDOWN = "//select[@data-test='product-sort-container']";
    public static final String ADD_TO_CART_BY_NAME = "//div[text()='%s']/ancestor::div[@class='inventory_item']//button";
    public static final String ADD_TO_CART_BUTTON = "//button[contains(@data-test,'add-to-cart')]";
    public static final String CART_BADGE = "//span[@class='shopping_cart_badge']";
    public static final String CART_ICON = "//div[@id='shopping_cart_container']";
    public static final String REMOVE_BUTTON = "//button[contains(@data-test,'remove')]";
}
