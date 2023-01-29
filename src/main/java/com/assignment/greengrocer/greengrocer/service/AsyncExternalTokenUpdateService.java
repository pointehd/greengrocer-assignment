package com.assignment.greengrocer.greengrocer.service;

import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.persistance.redis.ExternalToken;
import com.assignment.greengrocer.greengrocer.persistance.redis.ExternalTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AsyncExternalTokenUpdateService {

    private final ExternalTokenRepository repository;

    @Async
    public void saveToken(GreengrocerType greengrocerType, String token) {
        ExternalToken externalToken = repository.save(new ExternalToken(greengrocerType, token));
        log.info("token save: {}", externalToken.getValue());
    }

}
