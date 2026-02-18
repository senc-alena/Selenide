package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class AccountPage {

    final SelenideElement pageAccounts = $x("//*[text()='My Demo']");
    final SelenideElement spotBtn = $x("//*[text()='Spot']");
    final SelenideElement resetBtn = $x("//*[text()='Reset']");
    final SelenideElement totalValue = $x("//span[contains(text(), 'Total')]/following-sibling::span");

    public void pageOpen() {
        pageAccounts.should(exist).shouldBe(visible, Duration.ofSeconds(10));
        pageAccounts.shouldHave(text("My Demo"));
    }

    public void clickSpotBtn() {
        spotBtn.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public String getTotalValue() {
        return totalValue.shouldBe(visible).getText();
    }

    public void clickResetBtn() {
        resetBtn.should(exist).shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
