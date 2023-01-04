package rest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.example.rest.dto.Order;
import org.junit.jupiter.api.Assertions;

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
    @Test
    public void testOrderPostTest() {
        given()
                .accept("*/*")
                .contentType("application/json")
                .body("""
                        {"status": "OPEN",
                          "courierId": 0,
                          "customerName": "string",
                          "customerPhone": "string",
                          "comment": "string",
                          "id": 0}""")
                .when()
                .post("http://51.250.6.164:8080/test-orders/")
                .then()
                .statusCode(200)
                .body("status", equalTo("OPEN"));

    }
    @Test
    public void serializedOrderTest() {


        Order requestOrderConstructed = new Order("OPEN", 123L, "Pavel", "1234567890", "comment");

        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(requestOrderConstructed);

        given().contentType("application/json")
                .body(stringRequestOrder)
                .when()
                .post("http://51.250.6.164:8080/test-orders/")
                .then()
                .statusCode(200)
                .body("status", equalTo(requestOrderConstructed.getStatus()));
    }

    @Test
    public void deserializedOrderTest() {
        Order requestOrder = new Order();

        requestOrder.setStatus ("OPEN");
        requestOrder.setCustomerName("Pavel");
        requestOrder.setCustomerPhone("1234567890");
        requestOrder.setCourierId(123L);
        requestOrder.setComment("comment");

        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(requestOrder);

        Response response =
                given()
                        .contentType("application/json")
                        .body(stringRequestOrder)
                        .when()
                        .post("http://51.250.6.164:8080/test-orders/")
                        .then()
                        .extract().response();

        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode, "Error");

        String responseBody = response.body().asString();
        Order responseOrder = gson.fromJson(responseBody, Order.class);

        Assertions.assertEquals("OPEN", responseOrder.getStatus(), "Error");
    }

}
