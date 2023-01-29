package com.assignment.greengrocer.greengrocer.external;

import com.assignment.greengrocer.greengrocer.model.PriceResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {
    private final Map<GreengrocerType, ExternalAdaptor> adaptors;

    ExternalService() {
        this.adaptors = new HashMap<>();
    }

    public void putAdaptor(GreengrocerType greengrocerType, ExternalAdaptor defaultAdaptor) {
        this.adaptors.put(greengrocerType, defaultAdaptor);
    }

    public List<String> getProducts(GreengrocerType greengrocerType) {
        return adaptors.get(greengrocerType).getCategory();
    }

    public PriceResponse getPrice(GreengrocerType greengrocerType, String name) {
        return adaptors.get(greengrocerType).getPrice(name);
    }

}
