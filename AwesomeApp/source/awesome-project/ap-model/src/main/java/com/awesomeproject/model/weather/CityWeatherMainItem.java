package com.awesomeproject.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = CityWeatherMainItem.Builder.class)
public class CityWeatherMainItem {

    private final Double temp;
    private final Double pressure;
    private final Double humidity;

    public CityWeatherMainItem(Builder b) {
        this.temp = b.temp;
        this.pressure = b.pressure;
        this.humidity = b.humidity;
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
        private Double temp;
        private Double pressure;
        private Double humidity;

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

        public CityWeatherMainItem build() {
            return new CityWeatherMainItem(this);
        }
    }
}
