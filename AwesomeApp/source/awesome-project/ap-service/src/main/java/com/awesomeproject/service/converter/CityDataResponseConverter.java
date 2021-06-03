package com.awesomeproject.service.converter;

import com.awesomeproject.model.weather.CityItem;
import com.awesomeproject.model.weather.CityWeatherItem;
import com.awesomeproject.model.weather.CityWeatherMainItem;
import com.awesomeproject.model.weather.response.CityDataItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

@Component
public class CityDataResponseConverter extends SelfRegisteringConverter<CityItem, CityDataItemResponse> {

    @Autowired
    public CityDataResponseConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public CityDataItemResponse convert(CityItem source) {
        return CityDataItemResponse.builder()
                .name(source.getName())
                .pressure(ofNullable(source.getMain()).map(CityWeatherMainItem::getPressure).orElse(null))
                .temp(ofNullable(source.getMain()).map(CityWeatherMainItem::getTemp).orElse(null))
                .humidity(ofNullable(source.getMain()).map(CityWeatherMainItem::getHumidity).orElse(null))
                .currentCondition(fetchCurrentCondition(source))
                .description(fetchDescription(source))
                .build();
    }

    private String fetchCurrentCondition(CityItem source) {
        return fetchCityWeatherItem(source).map(CityWeatherItem::getMain).orElse(null);
    }

    private String fetchDescription(CityItem source) {
        return fetchCityWeatherItem(source).map(CityWeatherItem::getDescription).orElse(null);
    }

    private Optional<CityWeatherItem> fetchCityWeatherItem(CityItem source) {
        return ofNullable(source.getWeather()).map(List::stream).map(Stream::findFirst).map(Optional::get);
    }
}
