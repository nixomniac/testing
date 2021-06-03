package com.awesomeproject.integration.weather;

import com.awesomeproject.integration.CommonRestConsumer;
import com.awesomeproject.model.weather.WeatherCurrentItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;


@Component
public class WeatherForecastConsumer extends CommonRestConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherForecastConsumer.class);

    private final String url;
    private final String authKey;

    public WeatherForecastConsumer(@Value("${rest-api.base-url.weather-forecast}") final String url,
                                   @Value("${rest-api.weather-auth.key}") final String authKey) {
        this.url = url;
        this.authKey = authKey;
    }

    public WeatherCurrentItem getWeatherCurrent(String lat, String lon, int count) {
        LOGGER.info("Getting current weather for lat={}, lon={}", lat, lon);

        URI uri = fromHttpUrl(url)
                .path("/data/2.5/find")
                .queryParam("lat", "{lat}")
                .queryParam("lon", "{lon}")
                .queryParam("cnt", "{count}")
                .queryParam("appid", "{authKey}")
                .build(lat, lon, count, authKey);

        return getRequest(uri, WeatherCurrentItem.class);
    }
}
