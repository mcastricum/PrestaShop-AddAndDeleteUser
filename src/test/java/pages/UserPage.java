package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {
    private WebDriver driver;
    public UserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="address-link")
    WebElement addressIcon;
    @FindBy(className="account")
    WebElement profileIcon;
//    @FindBy(className="material-icons")
//    WebElement createIcon;
    @FindBy(id="history-link")
    WebElement historyIcon;

    public void addressButton () {
        addressIcon.click();
    }
    public void goToPanel () {
        profileIcon.click();
    }
    public void goToHistory(){
        historyIcon.click();
    }
//    public void goToPanel () {
//        createIcon.click();
//    }
}