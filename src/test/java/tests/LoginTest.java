package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void checkPageOpen() {
        loginPage.openPage();
        loginPage.login();
        homePage.pageOpen();
        homePage.checkColor();
    }
}
