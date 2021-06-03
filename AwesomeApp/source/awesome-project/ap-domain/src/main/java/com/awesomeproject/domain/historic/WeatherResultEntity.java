package com.awesomeproject.domain.historic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "weather_condition_results")
public class WeatherResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "name")
    private String cityName;

    @Column(name = "current_condition")
    private String currentCondition;

    @Column(name = "description")
    private String description;

    @Column(name = "temp")
    private Double temp;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public WeatherResultEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public WeatherResultEntity setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public WeatherResultEntity setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public WeatherResultEntity setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WeatherResultEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getTemp() {
        return temp;
    }

    public WeatherResultEntity setTemp(Double temp) {
        this.temp = temp;
        return this;
    }

    public Double getPressure() {
        return pressure;
    }

    public WeatherResultEntity setPressure(Double pressure) {
        this.pressure = pressure;
        return this;
    }

    public Double getHumidity() {
        return humidity;
    }

    public WeatherResultEntity setHumidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public WeatherResultEntity setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherResultEntity that = (WeatherResultEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(cityName, that.cityName) && Objects.equals(currentCondition, that.currentCondition) && Objects.equals(description, that.description) && Objects.equals(temp, that.temp) && Objects.equals(pressure, that.pressure) && Objects.equals(humidity, that.humidity) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ipAddress, cityName, currentCondition, description, temp, pressure, humidity, createdDate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WeatherResultEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("ipAddress='" + ipAddress + "'")
                .add("cityName='" + cityName + "'")
                .add("currentCondition='" + currentCondition + "'")
                .add("description='" + description + "'")
                .add("temp=" + temp)
                .add("pressure=" + pressure)
                .add("humidity=" + humidity)
                .add("createdDate=" + createdDate)
                .toString();
    }
}
