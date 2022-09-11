package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather cache.
 */
public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private WeatherProvider weatherProvider;

    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    /**
     * Default constructor.
     */
    public WeatherCache() {
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */

    public WeatherInfo getWeatherInfo(String city) {

        long deltaTime = 300000;
        try {
            synchronized (cache) {
                if (cache.containsKey(city) && (findDeltaTime(city) >= deltaTime)) {
                    removeWeatherInfo(city);
                    cache.put(city, weatherProvider.get(city));
                } else if (!cache.containsKey(city))
                    cache.put(city, weatherProvider.get(city));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println("City with name: " + "'" + city + "'" + " not found.");
            return null;
        }
        return cache.get(city);
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {

        cache.remove(city);
    }

    private long findDeltaTime(String city) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expiryTime = cache.get(city).getExpiryTime();
        return Duration.between(expiryTime, currentTime).toMillis();
    }
}
