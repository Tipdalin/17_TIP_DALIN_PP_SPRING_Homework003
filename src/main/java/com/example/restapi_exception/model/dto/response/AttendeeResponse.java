package com.example.restapi_exception.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeResponse {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
    private Integer eventId;
}
