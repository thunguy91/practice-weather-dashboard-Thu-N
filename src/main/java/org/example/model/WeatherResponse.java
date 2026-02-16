package org.example.model;

public class WeatherResponse {

        private String city;
        private double temperature;
        private String description;
        private int humidity;

        public WeatherResponse(String city,
                               double temperature,
                               String description,
                               int humidity) {
            this.city = city;
            this.temperature = temperature;
            this.description = description;
            this.humidity = humidity;
        }

        public String getCity() {
            return city;
        }

        public double getTemperature() {
            return temperature;
        }

        public String getDescription() {
            return description;
        }

        public int getHumidity() {
            return humidity;
        }

        @Override
        public String toString() {
            return "\nCurrent Weather for " + city +
                    "\nTemperature: " + temperature + "°F" +
                    "\nCondition: " + description +
                    "\nHumidity: " + humidity + "%";
        }
    }


