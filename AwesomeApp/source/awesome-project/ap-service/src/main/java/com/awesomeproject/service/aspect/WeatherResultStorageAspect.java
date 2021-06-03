package com.awesomeproject.service.aspect;

import com.awesomeproject.domain.historic.WeatherResultEntity;
import com.awesomeproject.model.weather.response.WeatherItemResponse;
import com.awesomeproject.repository.WeatherRepository;
import com.awesomeproject.service.WeatherService;
import com.awesomeproject.service.converter.storage.WeatherEntityMapper;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WeatherResultStorageAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherResultStorageAspect.class);

    private final WeatherRepository weatherRepository;
    private final WeatherEntityMapper weatherEntityMapper;
    private final WeatherService weatherService;

    public WeatherResultStorageAspect(WeatherRepository weatherRepository,
                                      WeatherEntityMapper weatherEntityMapper,
                                      WeatherService weatherService) {
        this.weatherRepository = weatherRepository;
        this.weatherEntityMapper = weatherEntityMapper;
        this.weatherService = weatherService;
    }

    @Async
    @AfterReturning(pointcut = "execution(* com.awesomeproject.service.WeatherService.getWeatherForecast(..)) " +
            "&& args(ipAddress)", returning = "weatherItemResponse")
    public void storeWeatherResult(WeatherItemResponse weatherItemResponse, String ipAddress) {

        if (weatherService.fetchResultFromStorage(ipAddress) == null) {
            LOGGER.info("Storing result for IP={}", ipAddress);
            WeatherResultEntity weatherResultEntity = weatherEntityMapper.mapToWeatherResultEntity(weatherItemResponse, ipAddress);
            weatherRepository.save(weatherResultEntity);
        }

    }
}
