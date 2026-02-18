package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;

public class AccountsTest extends BaseTest {

    @BeforeMethod
    public void openAccountPage() {
        loginPage.openPage();
        loginPage.login();
        homePage.header.goToAccounts();
    }

    @Test
    public void checkAccountsPageOpens() {
        homePage.header.goToAccounts();
        accountPage.pageOpen();
    }

    @Test
    public void checkSpotUpdates() {
        homePage.header.goToAccounts();
        accountPage.pageOpen();

        String totalBefore = accountPage.getTotalValue();
        accountPage.clickSpotBtn();

        String totalAfter = accountPage.getTotalValue();

        assertNotEquals(totalBefore, totalAfter, "Total не изменился");
    }

    @Test
    public void checkClickResetBtn() {
        accountPage.clickResetBtn();
    }
}
