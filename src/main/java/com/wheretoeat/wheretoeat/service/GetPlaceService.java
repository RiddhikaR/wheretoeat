package com.wheretoeat.wheretoeat.service;

import org.springframework.http.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.wheretoeat.wheretoeat.dto.HeaderDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetPlaceService {

    private final RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey; 

    public String search(Double latitude, Double longitude, Double radius) {
      
        HeaderDto header = new HeaderDto();
        header.setIncludedTypes(List.of("restaurant"));
        header.setMaxResultCount(10);

       
        HeaderDto.LocationRestriction locationRestriction = new HeaderDto.LocationRestriction();
        HeaderDto.Circle circle = new HeaderDto.Circle();
        HeaderDto.Center center = new HeaderDto.Center();
        center.setLatitude(latitude);
        center.setLongitude(longitude);
        circle.setCenter(center);
        circle.setRadius(radius);
        locationRestriction.setCircle(circle);

        header.setLocationRestriction(locationRestriction);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Goog-Api-Key", apiKey);
        httpHeaders.set("X-Goog-FieldMask", "places.displayName,places.id,places.formattedAddress,places.googleMapsUri,places.priceRange,places.rating,places.types,places.userRatingCount,places.goodForGroups,places.currentOpeningHours,places.internationalPhoneNumber,places.photos");

        HttpEntity<HeaderDto> entity = new HttpEntity<>(header, httpHeaders);

       
        String url = "https://places.googleapis.com/v1/places:searchNearby";

        
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );

        
        return response.getBody();
    }
}
