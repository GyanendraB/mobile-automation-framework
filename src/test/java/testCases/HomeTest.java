package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import static listeners.TestListener.getTest;

public class HomeTest extends BaseTest {

    @Test
    public void verifyHomePageTexts() {

        HomePage homePage = new HomePage();   // 🔥 no driver passed

        Assert.assertTrue(
                homePage.isHowToUseDisplayed(),
                "'Read How to Use the App' text is not visible"
        );

        Assert.assertEquals(
                homePage.getHowToUseText(),
                "Read How to Use the App",
                "Home page text mismatch"
        );

        getTest().pass("Verified home page text");
    }
}
