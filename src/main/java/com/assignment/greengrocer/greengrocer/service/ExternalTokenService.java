package com.assignment.greengrocer.greengrocer.service;

import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.persistance.redis.ExternalToken;
import com.assignment.greengrocer.greengrocer.persistance.redis.ExternalTokenRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ExternalTokenService {

    private final ExternalTokenRepository repository;

    public void saveToken(GreengrocerType greengrocerType, String token) {
        ExternalToken externalToken = repository.save(new ExternalToken(greengrocerType, token));
        log.info("token save: {}", externalToken.getValue());
    }

    public Optional<ExternalToken> getTokenOptional(GreengrocerType greengrocerType) {
        return repository.findById(greengrocerType);
    }

}
