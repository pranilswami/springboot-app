package com.app.service.location;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

// This service converts latitude & longitude into an exact address using the Google Maps API.

@Service
public class AddressService {
    private final String GOOGLE_API_KEY = "YOUR_GOOGLE_MAPS_API_KEY";  // Replace with your key
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getAddressFromCoordinates(String latitude, String longitude) {
        String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude;
        return restTemplate.getForObject(url, Map.class);
    }
}

