package com.assignment.greengrocer.greengrocer.persistance.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VegetableRepositoryTest {

    @Autowired
    private VegetableRepository vegetableRepository;

    @Test
    void repositoryTest() {
        Vegetable vegetable = new Vegetable("test채소", 3000L);
        vegetableRepository.save(vegetable);
        vegetableRepository.delete(vegetable);
    }

}