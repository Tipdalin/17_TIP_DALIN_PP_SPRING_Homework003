package com.example.restapi_exception.service.impl;

import com.example.restapi_exception.exception.NotFoundException;
import com.example.restapi_exception.model.dto.request.AttendeeRequest;
import com.example.restapi_exception.model.dto.response.AttendeeResponse;
import com.example.restapi_exception.model.entity.Attendee;
import com.example.restapi_exception.repository.AttendeeRepository;
import com.example.restapi_exception.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Override
    public List<AttendeeResponse> getAllAttendees() {
        return attendeeRepository.findAllAttendees();
    }

    @Override
    public AttendeeResponse getAttendeeById(Integer attendeeId) {
        if (attendeeId == null) {
            throw new NotFoundException("Attendee not found");
        }

        return attendeeRepository.findAttendeeById();
    }

    @Override
    public void createAttendee(AttendeeRequest request) {
        Attendee attendee = new Attendee(
                null,
                request.getAttendeeName(),
                request.getEmail(),
                request.getEventId()
        );

        attendeeRepository.saveAttendee(attendee);
    }

    @Override
    public void updateAttendeeById(Integer attendeeId, AttendeeRequest request) {

        AttendeeResponse attendee = attendeeRepository.findAttendeeById();

        if (attendee == null) {
            throw new NotFoundException("Attendee not found");
        }

        attendee.setAttendeeName(request.getAttendeeName());
        attendee.setEmail(request.getEmail());
        attendee.setEventId(request.getEventId());

        attendeeRepository.updateAttendeeById();
    }

    @Override
    public void deleteAttendeeById(Integer attendeeId) {
        attendeeRepository.deleteAttendeeById(attendeeId);

    }
}
