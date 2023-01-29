package com.assignment.greengrocer.greengrocer.service;

import com.assignment.greengrocer.greengrocer.external.ExternalService;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GreengrocerService {

    private final ExternalService externalService;

    public String getToken(GreengrocerType greengrocerType) {
        return externalService.getToken(greengrocerType);
    }
}
