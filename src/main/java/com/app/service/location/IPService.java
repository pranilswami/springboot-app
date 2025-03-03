package com.app.service.location;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

// This service will get the public IP of the system making the request.

@Service
public class IPService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getPublicIP() {
        String url = "https://api64.ipify.org?format=json";
        Map<String, String> response = restTemplate.getForObject(url, Map.class);
        return response != null ? response.get("ip") : "UNKNOWN";
    }
}
