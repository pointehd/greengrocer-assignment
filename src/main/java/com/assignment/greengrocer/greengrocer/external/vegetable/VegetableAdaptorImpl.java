package com.assignment.greengrocer.greengrocer.external.vegetable;

import com.assignment.greengrocer.greengrocer.external.ExternalAdaptor;
import com.assignment.greengrocer.greengrocer.external.ExternalService;
import com.assignment.greengrocer.greengrocer.external.GreengrocerProperties;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class VegetableAdaptorImpl implements ExternalAdaptor {

    private final GreengrocerProperties greengrocerProperties;
    private final WebClient webClient;
    private final ExternalService externalService;


    VegetableAdaptorImpl(GreengrocerProperties greengrocerProperties, ExternalService externalService) {
        this.greengrocerProperties = greengrocerProperties;
        this.webClient = WebClient
            .builder()
            .baseUrl(greengrocerProperties.getGreengrocerUrl(GreengrocerType.VEGETABLE))
            .build();
        this.externalService = externalService;
    }

    @PostConstruct
    void init() {
        externalService.putAdaptor(GreengrocerType.VEGETABLE, this);
    }


    @Override
    public String getToken() {
        return null;
    }

    @Override
    public List<String> getCategory() {
        return null;
    }

    @Override
    public long getPrice(String name) {
        return 0;
    }
}
