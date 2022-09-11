package com.example;

import java.time.LocalDateTime;

/**
 * Weather info.
 */
public class WeatherInfo {

    private String city;

    /**
     * Short weather description
     * Like 'sunny', 'clouds', 'raining', etc
     */
    private String shortDescription;

    /**
     * Weather description.
     * Like 'broken clouds', 'heavy raining', etc
     */
    private String description;

    /**
     * Temperature.
     */
    private double temperature;

    /**
     * Temperature that fells like.
     */
    private double feelsLikeTemperature;

    /**
     * Wind speed.
     */
    private double windSpeed;

    /**
     * Pressure.
     */
    private double pressure;

    /**
     * Expiry time of weather info.
     * If current time is above expiry time then current weather info is not actual!
     */
    private LocalDateTime expiryTime;

    public Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private final WeatherInfo info = new WeatherInfo();

        public Builder withCity(String city) {
            info.city = city;
            return this;
        }

        public Builder withShortDescription(String shortDescription) {
            info.shortDescription = shortDescription;
            return this;
        }

        public Builder withDescription(String description) {
            info.description = description;
            return this;
        }

        public Builder withTemperature(double temperature) {
            info.temperature = temperature;
            return this;
        }

        public Builder withFeelsLikeTemperature(double feelsLikeTemperature) {
            info.feelsLikeTemperature = feelsLikeTemperature;
            return this;
        }

        public Builder withWindSpeed(double windSpeed) {
            info.windSpeed = windSpeed;
            return this;
        }

        public Builder withPressure(double pressure) {
            info.pressure = pressure;
            return this;
        }

        public Builder setExpiryTime() {
            info.expiryTime = LocalDateTime.now();
            return this;
        }

        public WeatherInfo build() {
            return info;
        }
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    @Override
    public String toString() {
        return "WeatherInfo:{" +
                "city='" + city + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                ", windSpeed=" + windSpeed +
                ", pressure=" + pressure +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
