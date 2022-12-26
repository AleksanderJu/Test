package rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {

//1. Добавьте в свой проект библиотеку Rest Assured
//2. Используя rest assured создайте тест с GET запросом /test-orders/{id} (для запроса можно использовать любой id от 1 до 10)
//3. В тесте проверьте статус код ответа и поля тела ответа status, currierId, id.
        @Test
        public void testOrderGetTest() {
            given()
                    .accept("*/*")
                    .when()
                    .get("http://51.250.6.164:8080/test-orders/{id}", 2)
                    .then()
                    .statusCode(200)
                    .body("status", equalTo("OPEN"),
                            "courierId", equalTo(null),
                            "id", equalTo(2));
        }
}
