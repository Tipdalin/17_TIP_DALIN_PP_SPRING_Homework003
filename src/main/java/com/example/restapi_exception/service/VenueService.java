package com.example.restapi_exception.service;

import com.example.restapi_exception.model.dto.request.VenueRequest;
import com.example.restapi_exception.model.dto.response.VenueResponse;

import java.util.List;

public interface VenueService {

     List<VenueResponse> getAllVenues(Integer page, Integer size);
     VenueResponse getVenueById(Integer venueId);
     void createVenue(VenueRequest request);
     void updateVenueById(Integer id, VenueRequest request);
     void deleteVenueById(Integer id);
}
