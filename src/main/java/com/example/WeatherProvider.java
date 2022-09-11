package com.example;

import com.jayway.jsonpath.JsonPath;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/**
 * Weather provider
 */
public class WeatherProvider {

    private RestTemplate restTemplate;
    private String appKey;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */

    public WeatherInfo get(String city) {
        String stringUrl = "http://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + appKey + "&units=metric";

        String info = restTemplate.getForObject(stringUrl, String.class);
        LinkedHashMap<String, String> descriptionMap = JsonPath.parse(info).read("$.weather.[0]");
        String temp = "" + JsonPath.parse(info).read("$.main.temp");
        String cityName = JsonPath.parse(info).read("$.name");
        String shortDescription = descriptionMap.get("main");
        String description = descriptionMap.get("description");
        String feels_like = "" + JsonPath.parse(info).read("$.main.feels_like");
        String speed = "" + JsonPath.parse(info).read("$.wind.speed");
        String pressure = "" + JsonPath.parse(info).read("$.main.pressure");

        return new WeatherInfo().builder()
                .withCity(cityName)
                .withShortDescription(shortDescription)
                .withDescription(description)
                .withTemperature(Double.parseDouble(temp))
                .withFeelsLikeTemperature(Double.parseDouble(feels_like))
                .withWindSpeed(Double.parseDouble(speed))
                .withPressure(Double.parseDouble(pressure))
                .setExpiryTime()
                .build();
    }
}
