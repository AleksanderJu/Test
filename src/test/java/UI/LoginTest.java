package UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ui.pages.CreateOrderPage;
import ui.pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest {
    // @Test
    //public void loginExceptionTest() {
    // open("http://51.250.6.164:3000/signin");
    //  $(By.id("username")).setValue("user");
    // $(By.id("password")).setValue("12345678");
    // $(By.xpath("//*[@data-name='signIn-button']")).click();
    // $(By.xpath("//*[@data-name='authorizationError-popup-close-button']")).shouldBe(Condition.visible);
    //  String errorMessage = $(By.xpath("//span[@class='error-popup__title']")).shouldBe(Condition.visible).getText();
    // Assertions.assertEquals(
    // "Incorrect credentials",
    //  errorMessage,
    // "Сообщение об ошибке отображается некорректно"
    // );

    // }
    LoginPage loginPage;

    @BeforeEach
    public void openStartPage() {
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
    }

    @Test
    public void loginSuccessfulTest() {
        LoginPage.inputLogin("user4");
        sleep(1000);
        LoginPage.inputPassword("hellouser4");
        sleep(1000);
        LoginPage.signIn();
        sleep(1000);
        LoginPage.checkCorrectSingIn();
        CreateOrderPage.checkPageTitle("Создать заказ");
    }

    @Test
    public void loginExceptionTest() {
        loginPage.login("username", "password");
        loginPage.checkIncorrectCredentialsText("Incorrect credentials");
    }
}