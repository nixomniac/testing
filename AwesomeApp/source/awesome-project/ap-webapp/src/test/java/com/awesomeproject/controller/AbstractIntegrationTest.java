package com.awesomeproject.controller;

import com.awesomeproject.webapp.WeatherForecastingApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@ActiveProfiles("test")
@SpringBootTest(classes = WeatherForecastingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "60000")
public abstract class AbstractIntegrationTest {

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    private WebTestClient webTestClient;

    private static final WireMockServer wireMockServer = new WireMockServer(options().port(7070));

    @BeforeAll
    static void init() {
        TomcatURLStreamHandlerFactory.getInstance();
        wireMockServer.start();
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    protected WebTestClient.ResponseSpec makeGetRequest(String path) {
        return webTestClient.get()
                .uri("/api/" + path)
                .exchange();
    }

    protected void mockApiGetCall(String path, String expectedResponse) {
        mockApiGetCall(path, expectedResponse, new HashMap<>());
    }

    protected void mockApiGetCall(String path, String expectedResponse, Map<String, StringValuePattern> queryParamMap) {
        wireMockServer.stubFor(
                get(urlPathEqualTo(path))
                        .withQueryParams(queryParamMap)
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withStatus(HttpStatus.OK.value())
                                .withBody(expectedResponse)));
    }
}
