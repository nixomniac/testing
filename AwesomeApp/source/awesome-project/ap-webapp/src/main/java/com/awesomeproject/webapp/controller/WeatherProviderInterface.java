package com.awesomeproject.webapp.controller;

import com.awesomeproject.model.location.IPLocationItem;
import com.awesomeproject.model.weather.response.WeatherItemResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface WeatherProviderInterface {
    @ApiOperation(value = "Get weather conditions by IP address", nickname = "getWeatherByIP", notes = "Get weather conditions by IP address", response = IPLocationItem.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation", response = IPLocationItem.class),
            @ApiResponse(code = 400, message = "Bad request")
    })
    ResponseEntity<WeatherItemResponse> getWeatherByIP(@PathVariable String ipAddress);

}
