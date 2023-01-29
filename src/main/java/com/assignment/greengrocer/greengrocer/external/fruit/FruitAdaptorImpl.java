package com.assignment.greengrocer.greengrocer.external.fruit;

import com.assignment.greengrocer.greengrocer.external.ExternalAdaptor;
import com.assignment.greengrocer.greengrocer.external.ExternalService;
import com.assignment.greengrocer.greengrocer.external.GreengrocerProperties;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FruitAdaptorImpl implements ExternalAdaptor {

    private final GreengrocerProperties greengrocerProperties;
    private final WebClient webClient;
    private final ExternalService externalService;


    FruitAdaptorImpl(GreengrocerProperties greengrocerProperties, ExternalService externalService) {
        this.greengrocerProperties = greengrocerProperties;
        this.webClient = WebClient
            .builder()
            .baseUrl(greengrocerProperties.getGreengrocerUrl(GreengrocerType.FRUIT))
            .build();
        this.externalService = externalService;
    }

    @PostConstruct
    void init() {
        externalService.putAdaptor(GreengrocerType.FRUIT, this);
    }


    @Override
    public String getToken() {
        String response = webClient.get()
            .uri("/token")
            .retrieve()
            .bodyToMono(FruitTokenResponse.class)
            .block()
            .getAccessToken();
        return response;
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
