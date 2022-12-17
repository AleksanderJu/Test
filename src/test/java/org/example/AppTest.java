package org.example;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    //1. В main или в тесте выполните деление на ноль
    //2. Обработайте исключение при делении на ноль
    public void divisionByZero() {
        try {
            System.out.println("Делим число на ноль");
            System.out.println(366/0);
            System.out.println("!");


        } catch (ArithmeticException e) {

            System.out.println("Программа перепрыгнула в блок catch!");
            System.out.println("Ошибка! Нельзя делить на ноль!");
        }
        int a = 10;
        int b = 0;

        try {
            int c = a / b;
        } catch(ArithmeticException e) {
            System.out.println(e);
        }
        System.out.println("Продолжение программы");
}

    }


