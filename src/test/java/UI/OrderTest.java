package UI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.CreateOrderPage;
import ui.pages.LoginPage;
import ui.pages.OrderStatusPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.$;

public class OrderTest {


    LoginPage loginPage;

    @BeforeEach
    public void openStartPage() {
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
    }

    @Test
    public void createOrderTest() {

        LoginPage.inputLogin("user4");
        sleep(1000);
        LoginPage.inputPassword("hellouser4");
        sleep(1000);
        LoginPage.signIn();
        sleep(1000);
        LoginPage.checkCorrectSingIn();

        CreateOrderPage.createOrder("Alex", "33322555666", "zzzzzz");
        CreateOrderPage.checkOrderCreatedText("Заказ создан!");
        sleep(1000);

        CreateOrderPage createOrderPage = new CreateOrderPage();
        String orderId = createOrderPage.getOrderId();
        OrderStatusPage orderStatusPage = createOrderPage.checkOrderStatusById(orderId);
        orderStatusPage.checkOrderStatus("OPEN");
        orderStatusPage.checkOrderDetails("Alex", "33322555666", "zzzzzz");
        System.out.println("Заказ создан и проверен.");
    }


}
