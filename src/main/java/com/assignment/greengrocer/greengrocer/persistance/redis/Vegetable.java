package com.assignment.greengrocer.greengrocer.persistance.redis;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "Vegetable") // timeToLine = 300 기획에 따른 만료시간 조정
public class Vegetable {

    @Id
    private String name;
    private long price;
    private LocalDateTime saveAt;

    public Vegetable(String name, long price) {
        this.name = name;
        this.price = price;
        this.saveAt = LocalDateTime.now();
    }
}
