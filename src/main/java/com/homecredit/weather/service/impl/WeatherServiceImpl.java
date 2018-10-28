package com.homecredit.weather.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.homecredit.weather.entity.WeatherLog;
import com.homecredit.weather.service.WeatherService;

public class WeatherServiceImpl implements WeatherService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public long insert(WeatherLog weatherLog) {
		entityManager.persist(weatherLog);
		return weatherLog.getId();
	}
	
	@Override
	public List<WeatherLog> findAllWeatherLog() {
		Query query = entityManager.createNamedQuery("find_all_weather_log", WeatherLog.class);
		return query.getResultList();
	}

}
