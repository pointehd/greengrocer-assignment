package com.assignment.greengrocer.greengrocer.external.fruit;

import com.assignment.greengrocer.greengrocer.external.ExternalAdaptor;
import com.assignment.greengrocer.greengrocer.external.ExternalService;
import com.assignment.greengrocer.greengrocer.external.GreengrocerProperties;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.model.PriceResponse;
import com.assignment.greengrocer.greengrocer.persistance.redis.ExternalToken;
import com.assignment.greengrocer.greengrocer.persistance.redis.ExternalTokenRepository;
import com.assignment.greengrocer.greengrocer.service.AsyncExternalTokenUpdateService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FruitAdaptorImpl implements ExternalAdaptor {

    private final WebClient webClient;
    private final ExternalService externalService;
    private final ExternalTokenRepository repository;
    private final AsyncExternalTokenUpdateService externalTokenUpdateService;

    private static final String AUTH_HEADER = "Authorization";


    FruitAdaptorImpl(
        GreengrocerProperties greengrocerProperties,
        ExternalService externalService,
        ExternalTokenRepository externalTokenRepository,
        AsyncExternalTokenUpdateService externalTokenUpdateService) {
        this.webClient = WebClient
            .builder()
            .baseUrl(greengrocerProperties.getGreengrocerUrl(GreengrocerType.FRUIT))
            .build();
        this.externalService = externalService;
        this.repository = externalTokenRepository;
        this.externalTokenUpdateService = externalTokenUpdateService;
    }

    @PostConstruct
    void init() {
        externalService.putAdaptor(GreengrocerType.FRUIT, this);
    }


    // TODO getToken() -> redis data
    @Override
    public String getToken() {
        Optional<ExternalToken> optionalToken = repository.findById(GreengrocerType.FRUIT);
        if (optionalToken.isPresent()) {
            return optionalToken.get().getValue();
        }
        return getNewToken();
    }

    private String getNewToken() {
        String token = webClient.get()
            .uri("/token")
            .retrieve()
            .bodyToMono(FruitTokenResponse.class)
            .block()
            .getAccessToken();
        externalTokenUpdateService.saveToken(GreengrocerType.FRUIT, token);
        return token;
    }

    @Override
    public List<String> getCategory() {
        return Arrays.stream(webClient.get()
                .uri("/product")
                .headers(httpHeaders -> httpHeaders.add(AUTH_HEADER, getToken()))
                .retrieve()
                .bodyToMono(String[].class)
                .block())
            .toList();

    }

    @Override
    public PriceResponse getPrice(String name) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/product").queryParam("name", name).build())
            .headers(httpHeaders -> httpHeaders.add(AUTH_HEADER, getToken()))
            .retrieve()
            .bodyToMono(PriceResponse.class)
            .block();
    }
}
