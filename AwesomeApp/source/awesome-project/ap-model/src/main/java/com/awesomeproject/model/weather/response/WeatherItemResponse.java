package com.awesomeproject.model.weather.response;

import java.util.List;

public class WeatherItemResponse {

    private final List<CityDataItemResponse> nearCities;

    public WeatherItemResponse(Builder b) {
        this.nearCities = b.nearCities;
    }

    public List<CityDataItemResponse> getNearCities() {
        return nearCities;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<CityDataItemResponse> nearCities;

        public Builder nearCities(List<CityDataItemResponse> nearCities) {
            this.nearCities = nearCities;
            return this;
        }

        public WeatherItemResponse build() {
            return new WeatherItemResponse(this);
        }
    }
}
