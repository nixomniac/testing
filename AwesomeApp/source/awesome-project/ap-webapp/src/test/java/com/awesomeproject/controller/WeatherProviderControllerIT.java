package com.awesomeproject.controller;

import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.assertj.core.api.Assertions.assertThat;

class WeatherProviderControllerIT extends AbstractIntegrationTest {

    private static final String WEATHER_URI = "/weather/ip/";

    @Test
    @DisplayName("Successfully get forecast by ip")
    void getWeatherByIPTest() {
        String inputIPAddress = "11.22.33.44";

        String ipLocationResponse = TestHelper.getContentFromFile("/json/ipLocationResponse.json");
        String weatherForecastResponse = TestHelper.getContentFromFile("/json/weatherForecastResponse.json");
        String expectedResponse = TestHelper.getContentFromFile("/json/expectedSuccessResponse.json");

        Map<String, StringValuePattern> queryParamMap = new HashMap<>();
        queryParamMap.put("lat", equalTo("56.9496"));
        queryParamMap.put("lon", equalTo("24.0978"));
        queryParamMap.put("cnt", equalTo("1"));
        queryParamMap.put("appid", equalTo("test777"));

        mockApiGetCall("/" + inputIPAddress + "/json", ipLocationResponse);
        mockApiGetCall("/data/2.5/find", weatherForecastResponse, queryParamMap);

        String responseBody = makeGetRequest(WEATHER_URI + "/" + inputIPAddress)
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody).isNotNull().isEqualTo(expectedResponse);
    }
}
