package com.assignment.greengrocer.greengrocer.external.vegetable;

import com.assignment.greengrocer.greengrocer.external.ExternalAdaptor;
import com.assignment.greengrocer.greengrocer.external.ExternalService;
import com.assignment.greengrocer.greengrocer.external.GreengrocerProperties;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.model.PriceResponse;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@Component
public class VegetableAdaptorImpl implements ExternalAdaptor {

    private final WebClient webClient;
    private final ExternalService externalService;

    private static final String HEADER_COOKIE = "Authorization=";
    private static final String AUTH_HEADER = "Authorization";


    VegetableAdaptorImpl(
        GreengrocerProperties greengrocerProperties,
        ExternalService externalService) {
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
        ResponseEntity<String> responseCookie = webClient.get()
            .uri("/token")
            .retrieve()
            .toEntity(String.class)
            .block();
        log.debug("Response token Header: {}", responseCookie);
        return getHeaderToken(responseCookie);
    }

    private <T> String getHeaderToken(ResponseEntity<T> response) {
        String responseCookie = response.getHeaders()
            .get(HttpHeaders.SET_COOKIE)
            .get(0);
        return responseCookie.substring(HEADER_COOKIE.length(), responseCookie.indexOf(";"));
    }

    @Override
    public List<String> getCategory() {
        return Arrays.stream(webClient.get()
                .uri("/item")
                .headers(httpHeaders -> httpHeaders.add(AUTH_HEADER, getToken()))
                .retrieve()
                .bodyToMono(String[].class)
                .block())
            .toList();
    }

    @Override
    public PriceResponse getPrice(String name) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/item").queryParam("name", name).build())
            .headers(httpHeaders -> httpHeaders.add(AUTH_HEADER, getToken()))
            .retrieve()
            .bodyToMono(PriceResponse.class)
            .block();
    }
}
