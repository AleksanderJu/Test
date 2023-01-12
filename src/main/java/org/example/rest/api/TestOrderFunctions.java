package org.example.rest.api;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.example.rest.dto.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TestOrderFunctions {
    private String baseUrl;

    public TestOrderFunctions() {
        try (InputStream input = new FileInputStream("src/main/resources.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            baseUrl = properties.getProperty("baseUrl");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Order postNewOrder(Order body) {
        Gson gson = new Gson();
        String stringRequestBody = gson.toJson(body);

        Response response = given()
                .header("Content-type", "application/json")
                .body(stringRequestBody)
                .when()
                .post("http://51.250.6.164:8080/test-orders/")
                .then()
                .statusCode(200)
                .extract().response();
        return gson.fromJson(response.body().asString(), Order.class);
    }

    public Order postNewOrder(Order body, Map<String, String> headers) {
        Gson gson = new Gson();
        String stringRequestBody = gson.toJson(body);

        Response response = given()
                .headers(headers)
                .body(stringRequestBody)
                .when()
                .post("http://51.250.6.164:8080/orders/")
                .then()
                .statusCode(200)
                .extract().response();
        return gson.fromJson(response.body().asString(), Order.class);
    }


    public Order getOrderById(Map<String, String> headers, String orderId) {
        Gson gson = new Gson();
        Response responseOrderById = given()
                .headers(headers)
                .when()
                .get(baseUrl + "/orders/" + orderId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return gson.fromJson(responseOrderById.body().asString(), Order.class);

    }

    public int deleteOrderById(Map<String, String> headers, String orderId) {
        return given()
                .headers(headers)
                .when()
                .delete(baseUrl + "/orders/" + orderId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .statusCode();
    }
}
