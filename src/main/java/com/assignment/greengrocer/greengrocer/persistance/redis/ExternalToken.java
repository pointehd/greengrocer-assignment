package com.assignment.greengrocer.greengrocer.persistance.redis;

import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "token") // timeToLine = 300 토큰 만료시간에 따라 변경
public class ExternalToken {

    @Id
    private GreengrocerType greengrocerType;
    private String value;
    private LocalDateTime saveAt;

    public ExternalToken(GreengrocerType greengrocerType, String value) {
        this.greengrocerType = greengrocerType;
        this.value = value;
        this.saveAt = LocalDateTime.now();
    }
}
