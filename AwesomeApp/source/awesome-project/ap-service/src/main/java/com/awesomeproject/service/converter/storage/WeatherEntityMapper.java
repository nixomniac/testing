package com.awesomeproject.service.converter.storage;

import com.awesomeproject.domain.historic.WeatherResultEntity;
import com.awesomeproject.model.weather.response.CityDataItemResponse;
import com.awesomeproject.model.weather.response.WeatherItemResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

import static java.util.Optional.ofNullable;

@Component
public class WeatherEntityMapper {

    public WeatherResultEntity mapToWeatherResultEntity(WeatherItemResponse source, String ipAddress) {

        if (source == null) {
            return null;
        }

        CityDataItemResponse city = fetchFirstCity(source);

        return new WeatherResultEntity()
                .setCityName(fetchCityName(city))
                .setCurrentCondition(fetchCurrentCondition(city))
                .setDescription(fetchDescription(city))
                .setHumidity(fetchHumidity(city))
                .setPressure(fetchPressure(city))
                .setTemp(fetchTemp(city))
                .setIpAddress(ipAddress)
                .setCreatedDate(LocalDateTime.now());
    }

    public WeatherItemResponse mapToWeatherResponse(WeatherResultEntity source) {

        if (source == null) {
            return null;
        }

        return WeatherItemResponse.builder()
                .nearCities(Collections.singletonList(
                        CityDataItemResponse.builder()
                                .name(source.getCityName())
                                .currentCondition(source.getCurrentCondition())
                                .description(source.getDescription())
                                .temp(source.getTemp())
                                .pressure(source.getPressure())
                                .humidity(source.getHumidity())
                                .build()
                )).build();

    }

    private CityDataItemResponse fetchFirstCity(WeatherItemResponse source) {
        return source.getNearCities().stream().findFirst().orElse(null);
    }

    private String fetchCityName(CityDataItemResponse cityDataItemResponse) {
        return ofNullable(cityDataItemResponse).map(CityDataItemResponse::getName).orElse("");
    }

    private String fetchCurrentCondition(CityDataItemResponse cityDataItemResponse) {
        return ofNullable(cityDataItemResponse).map(CityDataItemResponse::getCurrentCondition).orElse("");
    }

    private String fetchDescription(CityDataItemResponse cityDataItemResponse) {
        return ofNullable(cityDataItemResponse).map(CityDataItemResponse::getDescription).orElse("");
    }

    private Double fetchHumidity(CityDataItemResponse cityDataItemResponse) {
        return ofNullable(cityDataItemResponse).map(CityDataItemResponse::getHumidity).orElse(null);
    }

    private Double fetchPressure(CityDataItemResponse cityDataItemResponse) {
        return ofNullable(cityDataItemResponse).map(CityDataItemResponse::getPressure).orElse(null);
    }

    private Double fetchTemp(CityDataItemResponse cityDataItemResponse) {
        return ofNullable(cityDataItemResponse).map(CityDataItemResponse::getTemp).orElse(null);
    }
}
