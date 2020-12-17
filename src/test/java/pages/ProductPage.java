package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    private WebDriver driver;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

        @FindBy(id = "quantity_wanted")
        WebElement productQty;

    @FindBy(className = "add-to-cart")
    WebElement productAdd;

    @FindBy(css = ".btn.btn-primary")
    WebElement toCheckoutButton;

    @FindBy(css = ".btn.btn-primary")
    WebElement toAddressButton;

    public void selectSize(String size) {
        Select sizeInput = new Select(driver.findElement(By.id("group_1")));
        sizeInput.selectByVisibleText(size);
    }
    public void selectQty(String qty) {
        productQty.click();
        productQty.clear();
        productQty.sendKeys(qty);
    }

    public void addToCart() {
        productAdd.click();
    }

    public void toCheckout() {
        toCheckoutButton.submit();
    }

    public void toAddress() {
        toAddressButton.click();
    }

}
