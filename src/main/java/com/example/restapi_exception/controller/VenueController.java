package com.example.restapi_exception.controller;

import com.example.restapi_exception.model.dto.request.VenueRequest;
import com.example.restapi_exception.model.dto.response.ApiResponse;
import com.example.restapi_exception.model.dto.response.VenueResponse;
import com.example.restapi_exception.model.entity.Venue;
import com.example.restapi_exception.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    //Get all venues
    @GetMapping
    public ResponseEntity<ApiResponse<List<VenueResponse>>> getAllVenues(@RequestParam Integer page, Integer size) {

        List<VenueResponse> data = venueService.getAllVenues(page, size);

        ApiResponse<List<VenueResponse>> response = ApiResponse.<List<VenueResponse>>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Retrieved venues successfully")
                .status(HttpStatus.OK.name())
                .payload(data)
                .build();
        return ResponseEntity.ok(response);
    }

    //Get venue by ID
    @GetMapping("/{venueId}")
    public ResponseEntity<ApiResponse<VenueResponse>> getVenueById(@PathVariable Integer venueId) {
        VenueResponse data = venueService.getVenueById(venueId);
        ApiResponse<VenueResponse> response = ApiResponse.<VenueResponse>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Retrieved venue id " + venueId + "successfully")
                .status(HttpStatus.OK.name())
                .payload(data)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createVenue(@RequestBody VenueRequest request) {
        venueService.createVenue(request);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Venue created successfully")
                .status(HttpStatus.CREATED.name())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Integer venueId,
                         @RequestBody VenueRequest request) {

        venueService.updateVenueById(venueId, request);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Venue updated successfully")
                .status(HttpStatus.OK.name())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Void>> deleteVenueById(@PathVariable Integer venueId) {
        venueService.deleteVenueById(venueId);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Venue deleted successfully")
                .status(HttpStatus.OK.name())
                .build();
        return ResponseEntity.ok(response);
    }


}
