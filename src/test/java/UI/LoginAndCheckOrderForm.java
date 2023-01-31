package UI;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginAndCheckOrderForm {
    @Test
    public void loginAndCheckOrderForm(){

        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("....");
        $(By.id("password")).setValue("....");
        $(By.xpath("//*[@data-name='signIn-button']")).click();
        $(By.xpath("//*[@data-name='openStatusPopup-button']")).shouldBe(Condition.enabled);
        $(By.xpath("//*[@data-name='createOrder-button']")).shouldBe(Condition.enabled);


    }
}
