package UI;


//Реализовать интеграционный тест:
//1. Создание заказа через UI (получить order id)
//2. Проверить наличие и детали заказа через API (используя order id из первого пункта)
import io.restassured.response.Response;
import org.example.rest.api.LoginFunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.CreateOrderPage;
import ui.pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class E2EIntegrationTest {
    private static final Map<String,String> headers = new HashMap<>();

    @BeforeAll
    public static void setup() {
        headers.put("Content-type", "application/json");

        LoginFunction loginFunctions = new LoginFunction();
        String token = loginFunctions.getToken();
        headers.put("Authorization", "Bearer " + token);
    }

    @BeforeEach
    public void openStartPage() {
        open("http://51.250.6.164:3000/signin", LoginPage.class);
    }

    //1. Создание заказа через UI (получить order id)
    @Test
    public void createOrderTest() {
        LoginFunction loginFunctions = new LoginFunction();
        LoginPage.inputLogin(loginFunctions.username);
        LoginPage.inputPassword(loginFunctions.password);
        LoginPage.signIn();
        LoginPage.checkCorrectSingIn();

        CreateOrderPage.createOrder("Alex", "33322555666", "zzzzzz");
        CreateOrderPage.checkOrderCreatedText("Заказ создан!");

        CreateOrderPage createOrderPage = new CreateOrderPage();
        String orderId = createOrderPage.getOrderId();
        Assertions.assertEquals(4, orderId.length());

//2. Проверить наличие и детали заказа через API (используя order id из первого пункта)
        Response responseOrderById = given()
                .headers(headers)
                .when()
                .get(loginFunctions.getBaseUrl() + "/orders/" + orderId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assertions.assertEquals(200, responseOrderById.statusCode());
        System.out.println(responseOrderById.body().asString());
    }
}