package com.awesomeproject.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = CityItem.Builder.class)
public class CityItem {
    private final String name;
    private final CityWeatherMainItem main;
    private final List<CityWeatherItem> weather;

    public CityItem(Builder b) {
        this.name = b.name;
        this.main = b.main;
        this.weather = b.weather;
    }

    public String getName() {
        return name;
    }

    public CityWeatherMainItem getMain() {
        return main;
    }

    public List<CityWeatherItem> getWeather() {
        return weather;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private String name;
        private CityWeatherMainItem main;
        private List<CityWeatherItem> weather;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder main(CityWeatherMainItem main) {
            this.main = main;
            return this;
        }

        public Builder weather(List<CityWeatherItem> weather) {
            this.weather = weather;
            return this;
        }

        public CityItem build() {
            return new CityItem(this);
        }
    }
}
