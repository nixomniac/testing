package com.awesomeproject.webapp.controller;

import com.awesomeproject.model.weather.response.WeatherItemResponse;
import com.awesomeproject.service.WeatherService;
import com.awesomeproject.service.exception.WeatherServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/api/weather")
public class WeatherProviderController implements WeatherProviderInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherProviderController.class);
    private static final String INPUT_VALIDATION_ERR_TXT = "Provided IP address is not correct";

    private final WeatherService weatherService;

    public WeatherProviderController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    @GetMapping(value = "/ip/{ipAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherItemResponse> getWeatherByIP(@PathVariable String ipAddress) {
        LOGGER.info("Get weather by IP={}", ipAddress);
        validateInput(ipAddress);

        return ResponseEntity.ok(weatherService.getWeatherForecast(ipAddress));
    }

    private void validateInput(String ipAddress) {
        String regex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern p = Pattern.compile(regex);

        ofNullable(ipAddress)
                .filter(str -> p.matcher(str).matches())
                .orElseThrow(() -> new WeatherServiceException(INPUT_VALIDATION_ERR_TXT));
    }
}
