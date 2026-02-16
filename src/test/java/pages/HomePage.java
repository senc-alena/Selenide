package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.Header;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    public Header header = new Header();

    private final String DATE_LOCATOR_PATTERN = "//*[contains(@aria-label, '%s %d, %d')]";
    final SelenideElement pageTitle = $x("//*[@*='tw-text-[20px] tw-font-normal tw-text-[#3A3A3C]']");
    final SelenideElement futuresBtn = $x("//*[.='Futures']");
    final SelenideElement spotBtn = $x("//*[.='Spot']");
    final SelenideElement dataChoice =
            $x("//*[@class = 'tw-w-full tw-max-w-max tw-flex tw-flex-col tw-gap-2 !tw-max-w-full']");
    final SelenideElement dataSide = $("[data-align='center']");
    public final SelenideElement chart =
            $x("//*[@class='tw-flex tw-flex-1 tw-relative tw-w-full']//canvas[@data-zr-dom-id='zr_0']");
    final SelenideElement chartTooltip =
            $x("//div[contains(@style, 'position: absolute') and contains(@style, 'z-index: 9999999') " +
                    "and .//strong[contains(text(), 'USDT')]]");
    final SelenideElement authentication = $x("//*[@role='menu']//*[@role='switch']");
    final SelenideElement connectAuthentication = $("[role='dialog']");
    final SelenideElement assetsToken = $x("//*[@class='tw-flex tw-flex-col tw-gap-[24px] " +
            "tw-justify-between tw-flex-1']//*[text()='Token']");
    final SelenideElement assetsTable = $x("//h1[text()='Assets']/following::table[1]");

    public void pageOpen() {
        pageTitle.should(exist).shouldBe(visible, Duration.ofSeconds(10));
        pageTitle.shouldHave(text("Statistics"));
    }

    public void checkColor() {
        pageTitle.shouldHave(Condition.cssValue("color", "rgba(58, 58, 60, 1)"));
    }

    public void clickFuturesBtn() {
        futuresBtn.shouldBe(enabled, Duration.ofSeconds(10)).hover().click();
        futuresBtn.shouldHave(Condition.cssValue("color", "rgba(46, 205, 153, 1)"));
        spotBtn.shouldHave(Condition.cssValue("color", "rgba(100, 116, 139, 1)"));
    }

    public void clickDataChoice() {
        dataChoice.shouldBe(enabled, Duration.ofSeconds(10)).hover().click();
    }

    public boolean haveDataSide() {
        dataSide.should(exist).shouldBe(visible, Duration.ofSeconds(10));
        return true;
    }

    public void selectCustomDate(int day, String month, int year) {
        String locator = String.format(DATE_LOCATOR_PATTERN, month, day, year);
        $x(locator).shouldBe(visible).click();
    }

    public void selectCustomRange(int startDay, String startMonth, int startYear,
                                  int endDay, String endMonth, int endYear) {
        String startLocator = String.format(DATE_LOCATOR_PATTERN, startMonth, startDay, startYear);
        $x(startLocator).shouldBe(visible).click();

        String endLocator = String.format(DATE_LOCATOR_PATTERN, endMonth, endDay, endYear);
        actions().click($x(endLocator)).perform();
    }

    public void hoverOverChart() {
        chart.shouldBe(visible).hover();
    }

    public boolean verifyTooltipAppears() {
        chartTooltip.shouldBe(visible, Duration.ofSeconds(5));
        return true;
    }

    public String getTooltipDate() {
        return chartTooltip.$("h3").getText();
    }

    public String getTooltipValue() {
        return chartTooltip.$("strong").getText();
    }

    public void clickAuthenticationBtn() {
        authentication.shouldBe(enabled, Duration.ofSeconds(10)).hover().click();
    }

    public boolean haveConnectAuthentication() {
        connectAuthentication.should(exist).shouldBe(visible, Duration.ofSeconds(10));
        return true;
    }

    public List<String> getFirstColumnTexts() {
        return assetsTable.$$x(".//tr/td[1]").texts();
    }

    public void clickAssetsToken() {
        assetsToken.shouldBe(visible).click();
    }
}
