package com.assignment.greengrocer.greengrocer.persistance.redis;

import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExternalTokenRepositoryTest {

    @Autowired
    private ExternalTokenRepository externalTokenRepository;

    @Test
    void repositoryTest() {
        ExternalToken externalToken = new ExternalToken(GreengrocerType.VEGETABLE, "test");
        externalTokenRepository.save(externalToken);
    }

}