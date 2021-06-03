package com.awesomeproject.service;

import com.awesomeproject.domain.historic.WeatherResultEntity;
import com.awesomeproject.integration.location.IPLocationConsumer;
import com.awesomeproject.integration.weather.WeatherForecastConsumer;
import com.awesomeproject.model.location.IPLocationItem;
import com.awesomeproject.model.weather.WeatherCurrentItem;
import com.awesomeproject.model.weather.response.WeatherItemResponse;
import com.awesomeproject.repository.WeatherRepository;
import com.awesomeproject.service.converter.storage.WeatherEntityMapper;
import com.awesomeproject.service.exception.WeatherServiceException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.Optional.ofNullable;

@Service
public class WeatherService {

    private static final String IP_LOCATION_ERROR_TEXT = "Wrong data received from location provider";

    private final IPLocationConsumer ipLocationConsumer;
    private final WeatherForecastConsumer weatherForecastConsumer;
    private final ConversionService conversionService;
    private final WeatherRepository weatherRepository;
    private final WeatherEntityMapper weatherEntityMapper;

    private static final int SINGLE_CITY = 1;
    private static final int FIVE_MINUTES = 5;

    public WeatherService(IPLocationConsumer ipLocationConsumer,
                          WeatherForecastConsumer weatherForecastConsumer,
                          ConversionService conversionService,
                          WeatherRepository weatherRepository,
                          WeatherEntityMapper weatherEntityMapper
    ) {
        this.ipLocationConsumer = ipLocationConsumer;
        this.weatherForecastConsumer = weatherForecastConsumer;
        this.conversionService = conversionService;
        this.weatherRepository = weatherRepository;
        this.weatherEntityMapper = weatherEntityMapper;
    }

    public WeatherItemResponse getWeatherForecast(String ipAddress) {

        WeatherItemResponse weatherItemResponse = fetchResultFromStorage(ipAddress);
        if (weatherItemResponse != null) {
            return weatherItemResponse;
        }

        return fetchResultFromWeb(ipAddress);
    }

    public WeatherItemResponse fetchResultFromStorage(String ipAddress) {
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(FIVE_MINUTES);
        WeatherResultEntity weatherResultEntity = weatherRepository.getByIpAddressAndCreatedDateAfter(ipAddress, fiveMinutesAgo);
        return weatherEntityMapper.mapToWeatherResponse(weatherResultEntity);
    }

    private WeatherItemResponse fetchResultFromWeb(String ipAddress) {
        IPLocationItem ipLocationItem = fetchIPLocation(ipAddress);

        String lat = ofNullable(ipLocationItem)
                .map(IPLocationItem::getLatitude)
                .map(Object::toString)
                .orElseThrow(() -> new WeatherServiceException(IP_LOCATION_ERROR_TEXT));

        String lon = ofNullable(ipLocationItem)
                .map(IPLocationItem::getLongitude)
                .map(Object::toString)
                .orElseThrow(() -> new WeatherServiceException(IP_LOCATION_ERROR_TEXT));

        WeatherCurrentItem response = weatherForecastConsumer.getWeatherCurrent(lat, lon, SINGLE_CITY);
        return conversionService.convert(response, WeatherItemResponse.class);
    }

    private IPLocationItem fetchIPLocation(String ipAddress) {
        return ipLocationConsumer.getIPLocation(ipAddress);
    }
}
