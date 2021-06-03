package com.awesomeproject.model.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = IPLocationItem.Builder.class)
public class IPLocationItem {

    private final Double longitude;
    private final Double latitude;
    private final String city;
    private final String countryName;

    public IPLocationItem(Builder b) {
        this.longitude = b.longitude;
        this.latitude = b.latitude;
        this.city = b.city;
        this.countryName = b.countryName;
    }

    public String getCity() {
        return city;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public static IPLocationItem.Builder builder() {
        return new IPLocationItem.Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private Double longitude;
        private Double latitude;
        private String city;
        @JsonProperty("country_name")
        private String countryName;

        public Builder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder countryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public IPLocationItem build() {
            return new IPLocationItem(this);
        }
    }
}
