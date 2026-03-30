package com.example.restapi_exception.service;

import com.example.restapi_exception.model.dto.request.AttendeeRequest;
import com.example.restapi_exception.model.dto.response.AttendeeResponse;

import java.util.List;

public interface AttendeeService {
    List<AttendeeResponse> getAllAttendees();
    AttendeeResponse getAttendeeById(Integer attendeeId);
    void createAttendee(AttendeeRequest request);
    void updateAttendeeById(Integer attendeeId, AttendeeRequest request);
    void deleteAttendeeById(Integer attendeeId);
}
