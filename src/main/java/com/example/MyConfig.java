package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:app.properties")
public class MyConfig {

    @Value("${weatherProvider.appKey}")
    private String appKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherProvider weatherProvider() {
        WeatherProvider weatherProvider = new WeatherProvider();
        weatherProvider.setRestTemplate(restTemplate());
        weatherProvider.setAppKey(appKey);
        return weatherProvider;
    }

    @Bean
    public WeatherCache weatherCache() {
        WeatherCache weatherCache = new WeatherCache();
        weatherCache.setWeatherProvider(weatherProvider());
        return weatherCache;
    }
}
