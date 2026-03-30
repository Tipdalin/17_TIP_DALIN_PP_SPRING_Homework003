package com.example.restapi_exception.service;

import com.example.restapi_exception.model.dto.request.EventRequest;
import com.example.restapi_exception.model.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(Integer eventId);
    void createEvent(EventRequest request);
    void updateEvent(Integer eventId, EventRequest request);
    void deleteEvent(Integer eventId);
}
