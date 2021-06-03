package com.awesomeproject.service.converter;

import com.awesomeproject.model.weather.CityItem;
import com.awesomeproject.model.weather.WeatherCurrentItem;
import com.awesomeproject.model.weather.response.CityDataItemResponse;
import com.awesomeproject.model.weather.response.WeatherItemResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.awesomeproject.service.helper.TestHelper.getCityDataItemResponse;
import static com.awesomeproject.service.helper.TestHelper.getWeatherCurrentItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class WeatherResponseConverterTest {

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private WeatherResponseConverter weatherResponseConverter;

    @Test
    void convertSuccessTest() {
        WeatherCurrentItem input = getWeatherCurrentItem();
        CityItem cityItemInput = input.getList().get(0);

        CityDataItemResponse cityDataItemResponse = getCityDataItemResponse();
        when(conversionService.convert(cityItemInput, CityDataItemResponse.class))
                .thenReturn(cityDataItemResponse);

        WeatherItemResponse result = weatherResponseConverter.convert(input);

        assertThat(result).isNotNull();
        assertThat(result.getNearCities()).isNotNull();
        assertThat(result.getNearCities().size()).isEqualTo(1);

        CityDataItemResponse nearCityResult = result.getNearCities().get(0);

        assertThat(nearCityResult.getName()).isEqualTo(cityItemInput.getName());
        assertThat(nearCityResult.getCurrentCondition()).isEqualTo(cityItemInput.getWeather().get(0).getMain());
        assertThat(nearCityResult.getDescription()).isEqualTo(cityItemInput.getWeather().get(0).getDescription());
        assertThat(nearCityResult.getHumidity()).isEqualTo(cityItemInput.getMain().getHumidity());
        assertThat(nearCityResult.getTemp()).isEqualTo(cityItemInput.getMain().getTemp());
        assertThat(nearCityResult.getPressure()).isEqualTo(cityItemInput.getMain().getPressure());
    }
}