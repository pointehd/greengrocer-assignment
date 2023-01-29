package com.assignment.greengrocer.greengrocer.persistance.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FruitRepositoryTest {

    @Autowired
    private FruitRepository fruitRepository;

    @Test
    void repositoryTest() {
        Fruit fruit = new Fruit("test", 3000L);
        fruitRepository.save(fruit);
        fruitRepository.delete(fruit);
    }
}