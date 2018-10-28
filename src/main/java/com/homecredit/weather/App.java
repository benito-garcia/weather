package com.homecredit.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.homecredit.weather.client.WeatherClient;
import com.homecredit.weather.dto.WeatherInfo;
import com.homecredit.weather.entity.WeatherLog;
import com.homecredit.weather.repository.WeatherLogRepository;

@SpringBootApplication
public class App implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Autowired
	private WeatherClient weatherClient;
	
	@Autowired
	private WeatherLogRepository weatherLogRepository;

    @Autowired
    private ConfigurableApplicationContext context;

	@Override
	public void run(String... args) throws Exception {
		WeatherInfo london = weatherClient.getWeather("London");
		WeatherInfo prague = weatherClient.getWeather("Prague");
		WeatherInfo sanFrancisco = weatherClient.getWeather("San Francisco");
		
		logger.info("Location: " + london.getName());
		logger.info("Actual Weather: " + london.getWeather().get(0).getMain());	
		logger.info("Temp: " + london.getMain().getTemp());
		logger.info("==================================================");
		logger.info("Location: " + prague.getName());
		logger.info("Actual Weather: " + prague.getWeather().get(0).getMain());	
		logger.info("Temp: " + prague.getMain().getTemp());
		logger.info("==================================================");
		logger.info("Location: " + sanFrancisco.getName());
		logger.info("Actual Weather: " + sanFrancisco.getWeather().get(0).getMain());	
		logger.info("Temp: " + sanFrancisco.getMain().getTemp());
		
		List<WeatherLog> allWeatherLogs = weatherLogRepository.findAll();
		
		if(allWeatherLogs.size() > 2) {
			for(int x = allWeatherLogs.size(); x > 2; x--) {
				weatherLogRepository.delete(allWeatherLogs.get(x-1));
			}
		}
		
		weatherLogRepository.save(createWeatherLog(london));
		weatherLogRepository.save(createWeatherLog(prague));
		weatherLogRepository.save(createWeatherLog(sanFrancisco));
		
		context.close();
	}
	
	private WeatherLog createWeatherLog(WeatherInfo weatherInfo) {
		WeatherLog weatherLog = new WeatherLog();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		weatherLog.setResponseId(java.util.UUID.randomUUID().toString());
		weatherLog.setLocation(weatherInfo.getName());
		weatherLog.setActualWeather(weatherInfo.getWeather().get(0).getMain());
		weatherLog.setTemperature(Float.toString(weatherInfo.getMain().getTemp()));
		weatherLog.setDtimeInserted(sdf.format(date));
		return weatherLog;
	}

}
