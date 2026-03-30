package com.example.restapi_exception.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueResponse {
    private Integer venueId;
    private String venueName;
    private String location;
}


