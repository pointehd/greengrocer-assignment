package com.assignment.greengrocer.greengrocer.service;

import com.assignment.greengrocer.greengrocer.external.ExternalService;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.model.PriceResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GreengrocerService {

    private final ExternalService externalService;

    public List<String> getProduct(GreengrocerType greengrocerType) {
        return externalService.getProducts(greengrocerType);
    }

    public PriceResponse getPrice(GreengrocerType greengrocerType, String name) {
        return externalService.getPrice(greengrocerType, name);
    }
}
