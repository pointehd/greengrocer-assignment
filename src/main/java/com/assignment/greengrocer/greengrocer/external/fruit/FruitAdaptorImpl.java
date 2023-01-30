package com.assignment.greengrocer.greengrocer.external.fruit;

import com.assignment.greengrocer.common.model.exception.ApiErrorException;
import com.assignment.greengrocer.common.model.exception.ApiTokenException;
import com.assignment.greengrocer.greengrocer.external.ExternalAdaptor;
import com.assignment.greengrocer.greengrocer.external.ExternalService;
import com.assignment.greengrocer.greengrocer.external.GreengrocerProperties;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.model.PriceResponse;
import com.assignment.greengrocer.greengrocer.persistance.redis.ExternalToken;
import com.assignment.greengrocer.greengrocer.service.ExternalTokenService;
import io.netty.channel.ChannelOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class FruitAdaptorImpl implements ExternalAdaptor {

    private final WebClient webClient;
    private final ExternalService externalService;
    private final ExternalTokenService externalTokenService;

    private static final String AUTH_HEADER = "Authorization";


    FruitAdaptorImpl(
        GreengrocerProperties greengrocerProperties,
        ExternalService externalService,
        ExternalTokenService externalTokenService) {
        HttpClient client = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000);
        this.webClient = WebClient
            .builder()
            .clientConnector(new ReactorClientHttpConnector(client))
            .baseUrl(greengrocerProperties.getGreengrocerUrl(GreengrocerType.FRUIT))
            .build();
        this.externalService = externalService;
        this.externalTokenService = externalTokenService;
    }

    @PostConstruct
    void init() {
        externalService.putAdaptor(GreengrocerType.FRUIT, this);
    }

    // TODO getToken() -> redis data
    @Override
    public String getToken() {
        Optional<ExternalToken> optionalToken = externalTokenService.getTokenOptional(
            GreengrocerType.FRUIT);
        if (optionalToken.isPresent()) {
            return optionalToken.get().getValue();
        }
        return getNewToken();
    }

    private String getNewToken() {
        String token = apiCallToken();
        externalTokenService.saveToken(GreengrocerType.FRUIT, token);
        return token;
    }

    private String apiCallToken() {
        return Objects.requireNonNull(webClient.get()
                .uri("/token")
                .retrieve()
                .bodyToMono(FruitTokenResponse.class)
                .block())
            .getAccessToken();
    }

    @Override
    public List<String> getCategory() {
        String[] result;
        try {
            result = apiCallCategory();
        } catch (ApiTokenException e) {
            getNewToken();
            result = apiCallCategory();
        }
        return List.of(result);
    }

    private String[] apiCallCategory() {
        return webClient.get()
            .uri("/product")
            .headers(httpHeaders -> httpHeaders.add(AUTH_HEADER, getToken()))
            .retrieve()
            .onStatus(
                httpStatus -> HttpStatus.BAD_REQUEST != httpStatus && httpStatus.isError(),
                ex -> Mono.error(new ApiErrorException("잘못된 요청입니다.")))
            .onStatus(
                httpStatus -> HttpStatus.BAD_REQUEST == httpStatus,
                ex -> Mono.error(new ApiTokenException("ApiToken Response Error")))
            .bodyToMono(String[].class)
            .doOnError(ApiTokenException.class,
                e -> {
                    throw e;
                })
            .block();
    }

    @Override
    public PriceResponse getPrice(String name) {
        PriceResponse result;
        try {
            result = apiCallPrice(name);
        } catch (ApiTokenException e) {
            getToken();
            result = apiCallPrice(name);
        }
        return result;
    }

    private PriceResponse apiCallPrice(String name) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/product").queryParam("name", name).build())
            .headers(httpHeaders -> httpHeaders.add(AUTH_HEADER, getToken()))
            .retrieve()
            .onStatus(
                httpStatus -> HttpStatus.BAD_REQUEST != httpStatus && httpStatus.isError(),
                ex -> Mono.error(new ApiErrorException("잘못된 요청입니다.")))
            .onStatus(
                httpStatus -> HttpStatus.BAD_REQUEST == httpStatus,
                ex -> Mono.error(new ApiTokenException("ApiToken Response Error")))
            .bodyToMono(PriceResponse.class)
            .doOnError(ApiTokenException.class,
                e -> {
                    throw e;
                })
            .block();
    }
}
