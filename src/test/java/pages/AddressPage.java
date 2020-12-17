package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddressPage {
    private WebDriver driver;
    public AddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name="alias")
    WebElement aliasInput;

    @FindBy(name="address1")
    WebElement addressInput;

    @FindBy(name="city")
    WebElement cityInput;

    @FindBy(name="postcode")
    WebElement postcodeInput;

    @FindBy(name="country")
    WebElement countryInput;

    @FindBy(name="phone")
    WebElement phoneInput;

    @FindBy(className="btn")
    WebElement saveButton;

    @FindBy(css = ".address-body")
    WebElement newAddressInformation;

    @FindBy(css = ".alert.alert-success")
    WebElement deletedAddressInformation;

    @FindBy(xpath = "//a[@data-link-action='delete-address']")
    WebElement deleteClick;


//    @FindBy(xpath = "//a[@data-link-action='add-address']")
//    WebElement createClick;


//    public void createAddress(){
//        createClick.click();
//    }

    public void addAddress(String alias, String address, String city, String postcode, String country, String phone) {
        aliasInput.click();
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.click();
        addressInput.clear();
        addressInput.sendKeys(address);

        cityInput.click();
        cityInput.clear();
        cityInput.sendKeys(city);

        postcodeInput.click();
        postcodeInput.clear();
        postcodeInput.sendKeys(postcode);

        Select countryInput = new Select(driver.findElement(By.name("id_country")));
        countryInput.selectByVisibleText(country);

        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        saveButton.click();
    }
    public String getSuccessInformation() {
        return newAddressInformation.getText();
    }

    public void deleteAddress(){
    deleteClick.click();

    }

    public String getDeleteInformation() {
        return deletedAddressInformation.getText();
    }
}