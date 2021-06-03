package com.awesomeproject.service.converter.storage;

import com.awesomeproject.domain.historic.WeatherResultEntity;
import com.awesomeproject.model.weather.response.CityDataItemResponse;
import com.awesomeproject.model.weather.response.WeatherItemResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.awesomeproject.service.helper.TestHelper.getWeatherItemResponse;
import static com.awesomeproject.service.helper.TestHelper.getWeatherResultEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class WeatherEntityMapperTest {

    @InjectMocks
    private WeatherEntityMapper weatherEntityMapper;

    @Test
    void mapToWeatherResultEntitySuccessTest() {
        WeatherItemResponse input = getWeatherItemResponse();
        String ipAddress = "11.22.33.44";

        WeatherResultEntity result = weatherEntityMapper.mapToWeatherResultEntity(input, ipAddress);

        CityDataItemResponse inputCity = input.getNearCities().get(0);

        assertThat(result).isNotNull();

        assertThat(result.getCityName()).isEqualTo(inputCity.getName());
        assertThat(result.getCurrentCondition()).isEqualTo(inputCity.getCurrentCondition());
        assertThat(result.getDescription()).isEqualTo(inputCity.getDescription());
        assertThat(result.getHumidity()).isEqualTo(inputCity.getHumidity());
        assertThat(result.getTemp()).isEqualTo(inputCity.getTemp());
        assertThat(result.getPressure()).isEqualTo(inputCity.getPressure());
    }

    @Test
    void mapToWeatherResponseSuccessTest() {
        WeatherResultEntity input = getWeatherResultEntity();

        WeatherItemResponse result = weatherEntityMapper.mapToWeatherResponse(input);

        assertThat(result).isNotNull();
        assertThat(result.getNearCities()).isNotNull();
        assertThat(result.getNearCities().size()).isEqualTo(1);

        CityDataItemResponse resultNearCity = result.getNearCities().get(0);

        assertThat(input.getCityName()).isEqualTo(resultNearCity.getName());
        assertThat(input.getCurrentCondition()).isEqualTo(resultNearCity.getCurrentCondition());
        assertThat(input.getDescription()).isEqualTo(resultNearCity.getDescription());
        assertThat(input.getHumidity()).isEqualTo(resultNearCity.getHumidity());
        assertThat(input.getTemp()).isEqualTo(resultNearCity.getTemp());
        assertThat(input.getPressure()).isEqualTo(resultNearCity.getPressure());
    }
}