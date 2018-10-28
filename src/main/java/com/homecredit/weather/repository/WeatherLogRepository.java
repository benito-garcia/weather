package com.homecredit.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homecredit.weather.entity.WeatherLog;

public interface WeatherLogRepository extends JpaRepository<WeatherLog, Long> {

}