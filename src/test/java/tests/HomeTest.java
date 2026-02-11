package tests;

import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void clickFuturesBtn() {
        loginPage.openPage();
        loginPage.login();
        homePage.clickFuturesBtn();
    }
}
