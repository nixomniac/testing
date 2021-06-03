package com.awesomeproject.repository;

import com.awesomeproject.domain.historic.WeatherResultEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherResultEntity, Long> {

    WeatherResultEntity getByIpAddressAndCreatedDateAfter(String ipAddress, LocalDateTime createdDateTime);
}
