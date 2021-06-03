package com.awesomeproject.integration;

import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WebClientTestUtils {
    @Mock
    protected WebClient webClient;
    @Mock
    protected WebClient.RequestHeadersSpec requestHeaderSpec;
    @Mock
    protected WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    protected WebClient.ResponseSpec responseSpec;

    protected <T> void mockGet(T responseBody) {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(URI.class))).thenReturn(requestHeaderSpec);
        when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(any(Class.class))).thenReturn(Mono.just(responseBody));
    }

    protected void verifyGetUri(String uri) {
        verify(webClient).get();
        verify(requestHeadersUriSpec).uri(URI.create(uri));
    }
}
