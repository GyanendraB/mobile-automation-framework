package pages;

import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage() {
        AppiumDriver driver = DriverManager.getDriver();   // 🔥 get thread-safe driver
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "Continue with E-mail")
    @iOSXCUITFindBy(accessibility = "Continue with E-mail")
    private WebElement continueWithEmailBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Read How to Use the App']")
    @iOSXCUITFindBy(xpath = "")
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
