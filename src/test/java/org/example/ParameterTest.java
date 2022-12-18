package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ParameterTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", useHeadersInDisplayName = true)
    void csvFileTestSource(String Login, String Password, String Role, String Country, String City){
        Assertions.assertNotNull(Login,Password);
        Assertions.assertFalse(City.isBlank(), String.valueOf(Country.isBlank()));
        Assertions.assertFalse(Role.isBlank());

    }
}
