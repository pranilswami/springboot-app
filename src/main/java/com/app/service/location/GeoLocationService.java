package com.app.service.location;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

// This service will get the latitude and longitude of a system using the public IP.

@Service
public class GeoLocationService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getGeoLocation(String ip) {
        String url = "http://ip-api.com/json/" + ip;
        return restTemplate.getForObject(url, Map.class);
    }
}

