package org.example.service;

import org.example.model.WeatherResponse;
import org.example.util.HttpClientUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private String apiKey;

    public WeatherResponse getWeather(String city) {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("WEATHER_API_KEY");

        try {
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

            String url =
                    "https://api.openweathermap.org/data/2.5/weather?q="
                            + encodedCity
                            + "&appid=" + apiKey
                            + "&units=imperial";

            String response =
                    HttpClientUtil.sendGetRequest(url);

            JSONObject json = new JSONObject(response);

            JSONObject main = json.getJSONObject("main");
            JSONArray weatherArray = json.getJSONArray("weather");

            double temp = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            String description =
                    weatherArray.getJSONObject(0)
                            .getString("description");

            return new WeatherResponse(
                    city,
                    temp,
                    description,
                    humidity
            );

        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("Failed to fetch weather");
        }
    }
}

