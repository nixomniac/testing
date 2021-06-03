package com.awesomeproject.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URI;

public class CommonRestConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRestConsumer.class);

    @Autowired
    private WebClient webClient;

    protected <R> R getRequest(URI uri, Class<R> responseClass) {
        return get(webClient, uri, responseClass)
                .block();
    }

    private <R> Mono<R> get(WebClient webClient, URI uri, Class<R> responseClass) {
        return makeRequest(webClient.get(), uri, responseClass);
    }

    private <R> Mono<R> makeRequest(RequestHeadersUriSpec webClientRequest, URI uri, Class<R> responseClass) {
        return webClientRequest
                .uri(uri)
                .retrieve()
                .bodyToMono(responseClass)
                .doOnError(this::handleError);
    }

    private void handleError(Throwable throwable) {
        LOGGER.error("Error while making http request. Message: {}", throwable.getMessage());
        if (throwable instanceof WebClientResponseException) {
            WebClientResponseException webClientResponseException = (WebClientResponseException) throwable;
            LOGGER.debug("Error while making http request. Detailed message: {}", webClientResponseException.getResponseBodyAsString());
            throw new HttpClientErrorException(webClientResponseException.getStatusCode(), webClientResponseException.getStatusText());
        }
    }
}
