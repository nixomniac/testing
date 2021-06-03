package com.awesomeproject.service.converter;

import com.awesomeproject.model.weather.WeatherCurrentItem;
import com.awesomeproject.model.weather.response.CityDataItemResponse;
import com.awesomeproject.model.weather.response.WeatherItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class WeatherResponseConverter extends SelfRegisteringConverter<WeatherCurrentItem, WeatherItemResponse> {

    @Autowired
    public WeatherResponseConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public WeatherItemResponse convert(WeatherCurrentItem source) {
        return WeatherItemResponse.builder()
                .nearCities(convertCollection(source.getList(), CityDataItemResponse.class))
                .build();
    }
}
