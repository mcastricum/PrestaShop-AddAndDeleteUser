package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(name="s")
    WebElement searchWidget;

    @FindBy(xpath="//*[@id=\"js-product-list\"]/div[1]/article[1]/div/div[1]/h2/a")
    WebElement chooseProduct;

    public void searchProduct (String productName) {
        searchWidget.click();
        searchWidget.sendKeys(productName);
        searchWidget.submit();
    }

    public void selectProduct () {
        chooseProduct.click();
    }
}
