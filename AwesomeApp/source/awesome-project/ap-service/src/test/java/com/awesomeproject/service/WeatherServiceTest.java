package com.awesomeproject.service;

import com.awesomeproject.domain.historic.WeatherResultEntity;
import com.awesomeproject.integration.location.IPLocationConsumer;
import com.awesomeproject.integration.weather.WeatherForecastConsumer;
import com.awesomeproject.model.location.IPLocationItem;
import com.awesomeproject.model.weather.WeatherCurrentItem;
import com.awesomeproject.model.weather.response.WeatherItemResponse;
import com.awesomeproject.repository.WeatherRepository;
import com.awesomeproject.service.converter.storage.WeatherEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static com.awesomeproject.service.helper.TestHelper.getIPLocationItem;
import static com.awesomeproject.service.helper.TestHelper.getWeatherCurrentItem;
import static com.awesomeproject.service.helper.TestHelper.getWeatherItemResponse;
import static com.awesomeproject.service.helper.TestHelper.getWeatherResultEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class WeatherServiceTest {

    @Mock
    private IPLocationConsumer ipLocationConsumer;
    @Mock
    private WeatherForecastConsumer weatherForecastConsumer;
    @Mock
    private ConversionService conversionService;
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private WeatherEntityMapper weatherEntityMapper;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    void getWeatherForecastTestStorage() {
        String ipAddress = "11.22.33.44";

        WeatherResultEntity weatherResultEntity = getWeatherResultEntity();
        when(weatherRepository.getByIpAddressAndCreatedDateAfter(eq(ipAddress), any(LocalDateTime.class)))
                .thenReturn(weatherResultEntity);
        WeatherItemResponse weatherItemResponse = getWeatherItemResponse();
        when(weatherEntityMapper.mapToWeatherResponse(weatherResultEntity))
                .thenReturn(weatherItemResponse);

        WeatherItemResponse result = weatherService.getWeatherForecast(ipAddress);

        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(weatherItemResponse);
    }

    @Test
    void getWeatherForecastTestWeb() {
        String ipAddress = "11.22.33.44";

        when(weatherRepository.getByIpAddressAndCreatedDateAfter(eq(ipAddress), any(LocalDateTime.class)))
                .thenReturn(null);

        IPLocationItem ipLocationItem = getIPLocationItem();
        when(ipLocationConsumer.getIPLocation(eq(ipAddress))).thenReturn(ipLocationItem);

        WeatherCurrentItem weatherCurrentItem = getWeatherCurrentItem();
        when(weatherForecastConsumer.getWeatherCurrent(ipLocationItem.getLatitude().toString(),
                ipLocationItem.getLongitude().toString(),
                1))
                .thenReturn(weatherCurrentItem);

        WeatherItemResponse weatherItemResponse = getWeatherItemResponse();
        when(conversionService.convert(weatherCurrentItem, WeatherItemResponse.class))
                .thenReturn(weatherItemResponse);

        WeatherItemResponse result = weatherService.getWeatherForecast(ipAddress);

        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(weatherItemResponse);
    }

}