package com.awesomeproject.integration.weather;

import com.awesomeproject.integration.WebClientTestUtils;
import com.awesomeproject.model.weather.WeatherCurrentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
class WeatherForecastConsumerTest extends WebClientTestUtils {

    @InjectMocks
    private WeatherForecastConsumer weatherForecastConsumer;

    @BeforeEach
    void init() {
        setField(weatherForecastConsumer, "url", "http://localhost:8080/api");
        setField(weatherForecastConsumer, "authKey", "cowabunga");
    }

    @Test
    void getIPLocationTest() {
        String lat = "777";
        String lon = "888";
        int count = 17;
        String expectedURL = "http://localhost:8080/api/data/2.5/find?lat=777&lon=888&cnt=17&appid=cowabunga";
        WeatherCurrentItem response = WeatherCurrentItem.builder().build();

        mockGet(response);

        var actualResponse = weatherForecastConsumer.getWeatherCurrent(lat, lon, count);

        verifyGetUri(expectedURL);
        assertThat(actualResponse).isSameAs(response);
    }
}
