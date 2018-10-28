package com.homecredit.weather.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.homecredit.weather.entity.WeatherLog;

@Repository
@Transactional
public interface WeatherService {
	
	public long insert(WeatherLog weatherLog);

	public List<WeatherLog> findAllWeatherLog();
}
