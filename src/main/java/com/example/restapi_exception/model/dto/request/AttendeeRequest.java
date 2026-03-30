package com.example.restapi_exception.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeRequest {
    private String attendeeName;
    private String email;
    private Integer eventId;
}
