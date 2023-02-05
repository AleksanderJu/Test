package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.$;

public class CreateOrderPage {

    private static final SelenideElement titleText = $(".new-order__title");

    private static final SelenideElement nameInput = $("#name");

    private static final SelenideElement phoneInput= $("#phone");

    private static final SelenideElement commentInput = $("#comment");

    private static final SelenideElement createOrderButton = $(By.xpath("//button[@class='button new-order__button']"));

    private static final SelenideElement popUpTextField = $(".notification-popup__text");

    private final SelenideElement popUpOrderIdField = $(".notification-popup__text", 1);
    private final SelenideElement popUpConfirmationButton = $(".notification-popup__button");
    private final SelenideElement openStatusPopUpButton = $(".header__button_check-order");
    private final SelenideElement searchByOrderIdInput = $(".order-search-popup__input");
    private final SelenideElement searchByOrderIdSubmitButton = $(".order-search-popup__button");



    //проверка заголовка страницы
    public static void checkPageTitle(String title) {
        titleText.shouldBe(Condition.visible).shouldHave(Condition.text(title));
    }
@Step("Create Order")
    //Создание заказа
    public static void createOrder(String name, String phone, String comment) {
        nameInput.setValue(name);
        phoneInput.setValue(phone);
        commentInput.setValue(comment);
        createOrderButton.click();
    }
@Step("checkOrderCreatedText")
    //проверка текста сообщения об успешном создании заказа
    public static void checkOrderCreatedText(String textSuccess) {
        popUpTextField
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(textSuccess));
    }
@Step("getOrderId")
    //получение id из статуса заказа
    public String getOrderId() {
        String orderId = $(".notification-popup__text", 1).getText().replaceAll("\\D+", "");
        popUpConfirmationButton.click();
        return orderId;
    }

    //проверка статуса заказа по id
    public OrderStatusPage checkOrderStatusById(String orderId) {
        openStatusPopUpButton.click();
        searchByOrderIdInput.setValue(orderId);
        searchByOrderIdSubmitButton.click();
        return Selenide.page(OrderStatusPage.class);
    }
    }

