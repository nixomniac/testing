package com.awesomeproject.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = WeatherCurrentItem.Builder.class)
public class WeatherCurrentItem {

    private final List<CityItem> list;

    public WeatherCurrentItem(Builder b) {
        this.list = b.list;
    }

    public List<CityItem> getList() {
        return list;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private List<CityItem> list;

        public Builder list(List<CityItem> list) {
            this.list = list;
            return this;
        }

        public WeatherCurrentItem build() {
            return new WeatherCurrentItem(this);
        }
    }
}
