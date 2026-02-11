package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    final SelenideElement pageTitle = $x("//*[@*='tw-text-[20px] tw-font-normal tw-text-[#3A3A3C]']");
    final SelenideElement futuresBtn = $x("//*[.='Futures']");

    public void pageOpen() {
        pageTitle.should(exist).shouldBe(visible, Duration.ofSeconds(10));
        pageTitle.shouldHave(text("Statistics"));
    }

    public void checkColor() {
        pageTitle.shouldHave(Condition.cssValue("color", "rgba(58, 58, 60, 1)"));
    }

    public void clickFuturesBtn() {
        futuresBtn.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }
}
