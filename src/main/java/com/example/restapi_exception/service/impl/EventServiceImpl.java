package com.example.restapi_exception.service.impl;

import com.example.restapi_exception.exception.NotFoundException;
import com.example.restapi_exception.model.dto.request.EventRequest;
import com.example.restapi_exception.model.entity.Event;
import com.example.restapi_exception.repository.EventRepository;
import com.example.restapi_exception.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;


    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAllEvents();
    }

    @Override
    public Event getEventById(Integer eventId) {
        Event event = eventRepository.findEventById(eventId);

        if (event == null) {
            throw new NotFoundException("Event not found");
        }

        return eventRepository.findEventById(eventId);

    }

    @Override
    public void createEvent(EventRequest request) {
        Event event = new Event(
                null,
                request.getEventName(),
                request.getEventDate(),
                request.getVenueId()
        );

        eventRepository.saveEvent(event);
    }

    @Override
    public void updateEvent(Integer eventId, EventRequest request) {
        Event event = eventRepository.findEventById(eventId);

        if (event == null) {
            throw new NotFoundException("Event not found");
        }

        event.setEventName(request.getEventName());
        event.setEventDate(request.getEventDate());
        event.setVenueId(request.getVenueId());

        eventRepository.updateEventById(event);

    }

    @Override
    public void deleteEvent(Integer eventId) {
        eventRepository.deleteEventById(eventId);

    }
}
