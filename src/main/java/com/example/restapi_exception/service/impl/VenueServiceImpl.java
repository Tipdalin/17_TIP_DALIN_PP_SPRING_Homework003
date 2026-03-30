package com.example.restapi_exception.service.impl;

import com.example.restapi_exception.exception.NotFoundException;
import com.example.restapi_exception.model.dto.request.VenueRequest;
import com.example.restapi_exception.model.dto.response.VenueResponse;
import com.example.restapi_exception.model.entity.Venue;
import com.example.restapi_exception.repository.VenueRepository;
import com.example.restapi_exception.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    public final VenueRepository venueRepository;

    @Override
    public List<VenueResponse> getAllVenues(Integer page, Integer size) {
        return venueRepository.findAllVenues( page,  size);
    }

    @Override
    public VenueResponse getVenueById(Integer venueId) {
        Venue venue = venueRepository.findVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue not found");
        }
        return new VenueResponse(venue.getVenueId(), venue.getVenueName(), venue.getLocation());
    }

    @Override
    public void createVenue(VenueRequest request) {
        Venue venue = new Venue(null, request.getVenueName(), request.getLocation());
        venueRepository.saveVenue(venue);
    }

    @Override
    public void updateVenueById(Integer venueId, VenueRequest request) {
        Venue venue = venueRepository.findVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue not found");
        }

        venue.setVenueName(request.getVenueName());
        venue.setLocation(request.getLocation());
        venueRepository.updateVenue(venueId, request);
    }

    @Override
    public void deleteVenueById(Integer venueId) {
        venueRepository.deleteVenueById(venueId);

    }


}
