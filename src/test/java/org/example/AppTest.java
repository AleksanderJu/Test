package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    //1. Создайте Java класс для тестов в папке test -> java
    //2. Используя классы Cat и Dog создайте 2 теста
    //3. Первый тест, используя assert, проверяет что Cat мяукает
    //4. Второй тест, используя assert, проверяет что Dog гавкает
    //
    //5* В pom файле добавьте maven-surefire-plugin и запустите все свои тесты командой mvn test

    @Test

    public void catTest() {
        Cat cat = new Cat();
        Assertions.assertEquals("Мяу-мяу", cat.sayMeow());
    }

    @Test
    public void dogTest() {
        Dog dog = new Dog();
        Assertions.assertEquals("Гав-гав", dog.sayWoof());
    }
}
