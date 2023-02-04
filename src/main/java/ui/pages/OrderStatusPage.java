package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



import static com.codeborne.selenide.Selenide.$;

public class OrderStatusPage {
    @FindBy(how = How.XPATH, using = "//h3[text() = 'Имя']/following-sibling::span")
    private SelenideElement nameTextField;

    @FindBy(how = How.XPATH, using = "//h3[text() = 'Телефон']/following-sibling::span")
    private SelenideElement phoneTextField;

    @FindBy(how = How.XPATH, using = "//h3[text() = 'Комментарий']/following-sibling::span")
    private SelenideElement commentTextField;
@Step("checkOrderStatus")
    //проверка статуса заказа (по значениям css и attribute элемента)
    public void checkOrderStatus(String status) {
        String xpathLocator = "//span[text() = '%s']";
        String attributeValue = $(By.xpath(String.format(xpathLocator, status)))
                .shouldBe(Condition.visible)
                .getAttribute("class");
        String colorValue = $(By.xpath(String.format(xpathLocator, status)))
                .shouldBe(Condition.visible)
                .getCssValue("background-color");
        String fontWeight = $(By.xpath(String.format(xpathLocator, status)))
                .shouldBe(Condition.visible)
                .getCssValue("font-weight");
        Assertions.assertEquals("rgba(253,204,0,1)", colorValue.replaceAll("\\s", ""));
        Assertions.assertEquals("700", fontWeight);
        Assertions.assertEquals("status-list__status status-list__status_active", attributeValue);
    }
@Step("checkOrderDetails")

    //проверка значений деталей заказа
    public void checkOrderDetails(String name, String phone, String comment) {
        Assertions.assertEquals(name, nameTextField.getText());
        Assertions.assertEquals(phone, phoneTextField.getText());
        Assertions.assertEquals(comment, commentTextField.getText());
    }
}
