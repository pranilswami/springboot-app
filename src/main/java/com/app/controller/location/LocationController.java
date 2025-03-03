package com.app.controller.location;

import com.app.service.location.AddressService;
import com.app.service.location.GeoLocationService;
import com.app.service.location.IPService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class LocationController {
    private final AddressService addressService;
    private final GeoLocationService geoLocationService;
    private final IPService ipService;

    public LocationController(AddressService addressService, GeoLocationService geoLocationService, IPService ipService) {
        this.addressService = addressService;
        this.geoLocationService = geoLocationService;
        this.ipService = ipService;
    }

    /*
     * Detect user's location using GPS (preferred) or IP (fallback)
     */
    @PostMapping("/detect-location")
    public Map<String, Object> detectUserLocation(@RequestBody(required = false) Map<String, Object> request, HttpServletRequest httpRequest) {
        if (request != null && request.containsKey("latitude") && request.containsKey("longitude")) {
            // Use GPS location (preferred)
            String latitude = request.get("latitude").toString();
            String longitude = request.get("longitude").toString();
            return getLocationDetails(latitude, longitude);
        }

        // If GPS is not available, use IP-based geolocation
        String publicIp = ipService.getPublicIP();
        Map<String, Object> geoData = geoLocationService.getGeoLocation(publicIp);

        if (geoData == null || !geoData.containsKey("lat") || !geoData.containsKey("lon")) {
            return Map.of("error", "Could not determine location from IP.");
        }

        String latitude = geoData.get("lat").toString();
        String longitude = geoData.get("lon").toString();
        return getLocationDetails(latitude, longitude);
    }

    /*
     * Convert Latitude & Longitude to Address
     */
    private Map<String, Object> getLocationDetails(String latitude, String longitude) {
        Map<String, Object> addressData = addressService.getAddressFromCoordinates(latitude, longitude);
        return Map.of(
                "latitude", latitude,
                "longitude", longitude,
                "addressData", addressData
        );
    }
}
