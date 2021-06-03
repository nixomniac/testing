package com.awesomeproject.model.weather.response;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class CityDataItemResponse {
    private final String name;
    private final String currentCondition;
    private final String description;
    private final Double temp;
    private final Double pressure;
    private final Double humidity;

    public CityDataItemResponse(Builder b) {
        this.name = b.name;
        this.currentCondition = b.currentCondition;
        this.description = b.description;
        this.temp = b.temp;
        this.pressure = b.pressure;
        this.humidity = b.humidity;
    }

    public String getName() {
        return name;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public String getDescription() {
        return description;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private String name;
        private String currentCondition;
        private String description;
        private Double temp;
        private Double pressure;
        private Double humidity;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder currentCondition(String currentCondition) {
            this.currentCondition = currentCondition;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder temp(Double temp) {
            this.temp = temp;
            return this;
        }

        public Builder pressure(Double pressure) {
            this.pressure = pressure;
            return this;
        }

        public Builder humidity(Double humidity) {
            this.humidity = humidity;
            return this;
        }

        public CityDataItemResponse build() {
            return new CityDataItemResponse(this);
        }
    }
}
