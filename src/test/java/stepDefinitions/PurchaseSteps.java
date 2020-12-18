package stepDefinitions;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PurchaseSteps {
    HomePage homePage;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    UserPage userPage;
    WebDriver driver;

public String orderId;

public String orderPrice;

    @Given("^User logged in to Presta Shop$")
    public void userLoggedInToPrestaShop() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("ijb@ijb.pl", "Password123");
    }

    @When("^User chooses (.*)$")
    public void userChoosesProduct(String productName) {
        homePage = new HomePage(driver);
        homePage.searchProduct(productName);
        homePage.selectProduct();

    }

    @And("^Check if discounted properly$")
    public void discountValueTest() {
        productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.priceAfterDiscount(), productPage.priceADShouldBe(),0.001);
    }

    @And("^User selects size (.*)$")
    public void userSelectsSize(String size) {
        productPage = new ProductPage(driver);
        productPage.selectSize(size);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("^User takes (.*) pcs$")
    public void userSelectsQty(String qty) {
        productPage = new ProductPage(driver);
        productPage.selectQty(qty);
    }

    @And("^User adds to cart$")
    public void userAddsProduct() {
        productPage = new ProductPage(driver);
        productPage.addToCart();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("^User goes to checkout$")
    public void userGoesToCheckout() {
        productPage = new ProductPage(driver);
        productPage.toCheckout();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.toAddress();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("^User confirms address$")
    public void userConfirmsAddress() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.confirmAddress();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("^User confirms delivery option$")
    public void userConfirmsDelivery() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.confirmDelivery();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("^User confirms payment method$")
    public void userConfirmsPayment() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.choosePayment();
        checkoutPage.confirmPayment();
    }

    @And("^User confirms purchase$")
    public void userConfirmsPurchase() {
        checkoutPage = new CheckoutPage(driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkoutPage.confirmPurchase();
    }

    @Then("^Purchase complete and shows (.*)$")
    public void userSees(String actionMessage) throws IOException {
        checkoutPage = new CheckoutPage(driver);
        orderId = checkoutPage.getOrderId();
        orderPrice = checkoutPage.getPrice();
        Assert.assertEquals(actionMessage, checkoutPage.getConfirmationInformation());

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss'-screenshot.png'").format(new Date());
        FileUtils.copyFile(scrFile, new File("screenshots/" + fileName));
//    driver.quit();
    }
        @And ("^User checks orders$")
        public void goToOrders() {
            userPage = new UserPage(driver);
            userPage.goToPanel();
            userPage.goToHistory();
        }

        @And ("^User finds new order$")
        public void lookUpOrder() {
            userPage = new UserPage(driver);
            System.out.println(userPage.getOrderDetails());
            Assert.assertTrue(userPage.getOrderDetails().contains(orderId));
            Assert.assertTrue(userPage.getOrderDetails().contains(orderPrice));
            Assert.assertTrue(userPage.getOrderDetails().contains("Awaiting check payment"));

        }

}
