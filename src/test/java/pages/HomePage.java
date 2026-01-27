package pages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private AppiumDriver driver;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "Continue with E-mail")
    @iOSXCUITFindBy(accessibility = "Continue with E-mail")
    private WebElement continueWithEmailBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\'Read How to Use the App\']")
    @iOSXCUITFindBy(xpath="")
    private WebElement readHowToUse;

    public void clickContinueWithEmail() {
    continueWithEmailBtn.click();
    }
    public String getHowToUseText() {
        return readHowToUse.getText();
    }
    public boolean isHowToUseDisplayed() {
        return readHowToUse.isDisplayed();
    }
}
