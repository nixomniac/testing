package com.awesomeproject.service.helper;

import com.awesomeproject.domain.historic.WeatherResultEntity;
import com.awesomeproject.model.location.IPLocationItem;
import com.awesomeproject.model.weather.CityItem;
import com.awesomeproject.model.weather.CityWeatherItem;
import com.awesomeproject.model.weather.CityWeatherMainItem;
import com.awesomeproject.model.weather.WeatherCurrentItem;
import com.awesomeproject.model.weather.response.CityDataItemResponse;
import com.awesomeproject.model.weather.response.WeatherItemResponse;

import static java.util.Collections.singletonList;

public class TestHelper {

    //todo static vars

    public static IPLocationItem getIPLocationItem() {
        return IPLocationItem.builder()
                .city("TestCity")
                .countryName("TestCountry")
                .latitude(11d)
                .longitude(22d)
                .build();
    }

    public static WeatherResultEntity getWeatherResultEntity() {
        return new WeatherResultEntity()
                .setCityName("TestCity")
                .setCurrentCondition("TestCond")
                .setDescription("TestDesc")
                .setTemp(17d)
                .setPressure(33d)
                .setHumidity(25d)
                .setIpAddress("11.22.33.44")
                .setId(123L);
    }

    public static WeatherItemResponse getWeatherItemResponse() {
        return WeatherItemResponse.builder()
                .nearCities(singletonList(getCityDataItemResponse()))
                .build();
    }

    public static WeatherCurrentItem getWeatherCurrentItem() {
        return WeatherCurrentItem.builder()
                .list(singletonList(getCityItem()))
                .build();
    }

    public static CityItem getCityItem() {
        return CityItem.builder()
                .name("TestCity")
                .main(getCityWeatherMainItem())
                .weather(singletonList(getCityWeatherItem()))
                .build();
    }

    private static CityWeatherMainItem getCityWeatherMainItem() {
        return CityWeatherMainItem.builder()
                .humidity(25d)
                .pressure(33d)
                .temp(17d)
                .build();
    }

    private static CityWeatherItem getCityWeatherItem() {
        return CityWeatherItem.builder()
                .main("TestCond")
                .description("TestDesc")
                .build();
    }

    public static CityDataItemResponse getCityDataItemResponse() {
        return CityDataItemResponse.builder()
                .name("TestCity")
                .currentCondition("TestCond")
                .description("TestDesc")
                .temp(17d)
                .humidity(25d)
                .pressure(33d)
                .build();
    }

}
