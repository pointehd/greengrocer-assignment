package com.assignment.greengrocer.greengrocer.external;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GreengrocerPropertiesTest {

    @Autowired
    private GreengrocerProperties greengrocerProperties;


    @DisplayName("properties 값이 받아지는지 테스트")
    @Test
    void configurationPropertiesTest() {
        assertNotNull(greengrocerProperties.getGreengrocerUrl(GreengrocerType.FRUIT));
        assertNotNull(greengrocerProperties.getGreengrocerUrl(GreengrocerType.VEGETABLE));
    }
}