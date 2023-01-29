package com.assignment.greengrocer.greengrocer.persistance.redis;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "fruit") // timeToLine = 300 토큰 만료시간에 따라 변경
public class ExternalToken {

    @Id
    private String name;
    private String value;
    private LocalDateTime saveAt;

    public ExternalToken(String name, String value) {
        this.name = name;
        this.value = value;
        this.saveAt = LocalDateTime.now();
    }
}
