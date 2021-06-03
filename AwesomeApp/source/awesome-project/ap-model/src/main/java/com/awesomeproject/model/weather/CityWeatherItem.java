package com.awesomeproject.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = CityWeatherItem.Builder.class)
public class CityWeatherItem {
    private final String main;
    private final String description;

    public CityWeatherItem(Builder b) {
        this.main = b.main;
        this.description = b.description;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private String main;
        private String description;

        public Builder() {
        }

        public Builder main(String main) {
            this.main = main;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public CityWeatherItem build() {
            return new CityWeatherItem(this);
        }
    }
}
