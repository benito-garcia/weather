package com.homecredit.weather.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.homecredit.weather.dto.WeatherInfo;

@Component
public class WeatherClient {
	
	@Autowired
	private RestOperations restOperations;
	
	private final String url;
	private final String key;
	
	@Autowired
	public WeatherClient(@Value("${open.weather.map.url}")String url, @Value("${open.weather.map.key}")String key) {
		this.url = url;
		this.key = key;
	}
	
	public WeatherInfo getWeather(String city) {
		return restOperations.getForObject(url, WeatherInfo.class, city, key);
	}

}