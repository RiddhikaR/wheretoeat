package com.wheretoeat.wheretoeat.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.wheretoeat.wheretoeat.dto.RequestDto;
import com.wheretoeat.wheretoeat.service.GetPlaceService;


@RequiredArgsConstructor
@RestController

public class FetchPlaceController {
    private final GetPlaceService getPlaceService;
   @PostMapping("/placesInfo")
    public ResponseEntity<String> getPlacesInfo(@RequestBody RequestDto requestDto) {
        
        String result = getPlaceService.search(requestDto.getLatitude(), requestDto.getLongitude(), requestDto.getRadius());

        return ResponseEntity.ok(result);  
    }
}
