package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    final SelenideElement userInput = $("[placeholder='Email']");
    final SelenideElement passwordInput = $x("//*[@type='password']");
    final SelenideElement loginBtn = $x("//*[@type='submit']");

    public void openPage() {
        open("login");
    }

    public void login() {
        userInput.setValue("senc-alena1@yandex.ru");
        passwordInput.sendKeys("Eybnfp1781");
        loginBtn.submit();
    }
}
