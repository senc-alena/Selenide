package pages.components;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Header {

    private final SelenideElement logo = $x("//*[@class='tw-flex tw-items-center tw-justify-between tw-py-[12px]']" +
            "//*[@class='tw-flex tw-items-center tw-gap-[8px]']");
    private final SelenideElement profileIcon = $("svg.lucide-user-cog");
    private final SelenideElement accountsLink = $("a[href='/my-accounts']");

    public void clickLogo() {
        logo.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public void clickProfile() {
        profileIcon.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    public void goToAccounts() {
        accountsLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
