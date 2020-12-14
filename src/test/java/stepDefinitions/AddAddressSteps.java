package stepDefinitions;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AddressPage;
import pages.LoginPage;
import pages.UserPage;
import java.util.concurrent.TimeUnit;

public class AddAddressSteps {
    AddressPage addressPage;
    UserPage userPage;
    WebDriver driver;

    @Given("^User logged in to CodersLab shop$")
    public void userLoggedInToCodersLabShop() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("ijb@ijb.pl", "Password123");
    }

    @When("^User goes to UserAddressesPage$")
    public void userGoesToUserAddressesPage() {
        userPage = new UserPage(driver);
        userPage.goToPanel ();
        userPage.addressButton ();
//        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=addresses");
    }

//    @And("^User creates new address$")
//    public void userCreatesNewAddress() {
//        addressPage = new AddressPage(driver);
//        addressPage.createAddress();
//    }

    @And("^User adds and saves new (.*), (.*), (.*), (.*), (.*) and (.*)$")
    public void userAddsNewAddress(String alias, String address, String city, String postcode, String country, String phone) {
        addressPage = new AddressPage(driver);
        addressPage.addAddress(alias, address, city, postcode, country, phone);
    }

    @Then("^User gets \"([^\"]*)\"$")
    public void addressAdded(String confirmationMessage) {
        Assert.assertEquals(confirmationMessage, addressPage.getSuccessInformation());
    }

    @And("User deletes address")
    public void deleteAddress (){
        addressPage.deleteAddress();
    }

    @Then ("User deleted address and gets \"([^\"]*)\"$")
    public void addressDeleted(String confirmationMessage) {
        Assert.assertEquals(confirmationMessage, addressPage.getDeleteInformation());
    }
}