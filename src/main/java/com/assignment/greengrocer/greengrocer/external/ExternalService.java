package com.assignment.greengrocer.greengrocer.external;

import java.util.HashMap;
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

    public String getToken(GreengrocerType greengrocerType) {
        return adaptors.get(greengrocerType).getToken();
    }

}
