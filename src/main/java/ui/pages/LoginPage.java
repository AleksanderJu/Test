package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class LoginPage {

    @FindBy(how = How.ID, using = "username")
    private static SelenideElement loginInput;

    @FindBy(how = How.ID, using = "password")
    private static SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = "//*[@data-name='signIn-button']")
    private static SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = "//*[@data-name='openStatusPopup-button']")
    private static SelenideElement openStatusButton;


    @FindBy(how = How.XPATH, using = "//*[@data-name='createOrder-button']")
    private static SelenideElement createOrderButton;

    @FindBy(how = How.XPATH, using = "//*[@data-name='authorizationError-popup-close-button']")
    private SelenideElement closePopUpButton;

    @FindBy(how = How.XPATH, using = "//span[@class='error-popup__title']")
    private SelenideElement errorPopUpTextField;

@Step("Login")
    //заполнение поля логин значением
    public static void inputLogin(String username) {
        loginInput.setValue(username);
    }
@Step("Password")
    //заполнение поля пароль значением
    public static void inputPassword(String password) {
        passwordInput.setValue(password);
    }
@Step("SingIn")
    //нажатие на кнопку логин
    public static CreateOrderPage signIn() {
        signInButton.click();
        return Selenide.page(CreateOrderPage.class);
    }

    public CreateOrderPage login(String username, String password) {
        inputLogin(username);
        inputPassword(password);
        signIn();
        return Selenide.page(CreateOrderPage.class);
    }

    public static void checkCorrectSingIn() {
        openStatusButton.shouldBe(Condition.enabled);
        sleep(1000);
        createOrderButton.shouldBe(Condition.enabled);
        sleep(1000);
    }

    public void checkIncorrectCredentialsText(String textError) {
        closePopUpButton.shouldBe(Condition.visible);
        errorPopUpTextField.shouldHave(Condition.text(textError));
    }
}
