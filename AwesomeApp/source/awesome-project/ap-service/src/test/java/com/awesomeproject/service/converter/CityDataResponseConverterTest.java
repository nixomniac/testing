package com.awesomeproject.service.converter;

import com.awesomeproject.model.weather.CityItem;
import com.awesomeproject.model.weather.response.CityDataItemResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.awesomeproject.service.helper.TestHelper.getCityItem;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class CityDataResponseConverterTest {

    @InjectMocks
    private CityDataResponseConverter cityDataResponseConverter;

    @Test
    void convertSuccessTest() {
        CityItem input = getCityItem();
        CityDataItemResponse result = cityDataResponseConverter.convert(input);

        assertThat(result).isNotNull();

        assertThat(result.getName()).isEqualTo(input.getName());
        assertThat(result.getCurrentCondition()).isEqualTo(input.getWeather().get(0).getMain());
        assertThat(result.getDescription()).isEqualTo(input.getWeather().get(0).getDescription());
        assertThat(result.getHumidity()).isEqualTo(input.getMain().getHumidity());
        assertThat(result.getTemp()).isEqualTo(input.getMain().getTemp());
        assertThat(result.getPressure()).isEqualTo(input.getMain().getPressure());
    }
}