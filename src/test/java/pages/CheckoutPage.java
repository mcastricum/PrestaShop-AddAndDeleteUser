package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CheckoutPage {
    private WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "confirm-addresses")
    WebElement addressConfirmButton;

    @FindBy(name = "confirmDeliveryOption")
    WebElement deliveryConfirmButton;

    @FindBy(id = "payment-option-1")
    WebElement paymentOption;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    WebElement termsConfirmButton;

    @FindBy(xpath = "//*[@id=\"payment-confirmation\"]/div[1]/button")
    WebElement purchaseConfirmation;

    @FindBy(xpath = "//*[@id=\"content-hook_order_confirmation\"]/div/div/div/h3")
    WebElement successInformation;

    @FindBy(xpath = "//*[@id=\"order-details\"]/ul/li[1]")
    WebElement orderId;

    @FindBy(xpath = "//*[@id=\"order-items\"]/div/table/tbody/tr[3]/td[2]")
    WebElement orderPrice;



    public void confirmAddress() {
        addressConfirmButton.click();
    }

    public void confirmDelivery() {
        deliveryConfirmButton.click();
    }

    public void choosePayment() {
        paymentOption.click();
    }

    public void confirmPayment() {
        termsConfirmButton.click();
    }

    public void confirmPurchase() {
        purchaseConfirmation.click();
    }

    public String getConfirmationInformation() {
        return successInformation.getText();
    }

    public String getOrderId() {
        return orderId.getText().substring(18);
    }

    public String getPrice() {
        return orderPrice.getText();
    }

}
