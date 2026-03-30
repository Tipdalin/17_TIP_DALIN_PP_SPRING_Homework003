package com.example.restapi_exception.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
    private Integer eventId;
}
