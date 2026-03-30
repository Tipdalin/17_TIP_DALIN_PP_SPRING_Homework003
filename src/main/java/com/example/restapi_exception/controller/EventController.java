package com.example.restapi_exception.controller;

import com.example.restapi_exception.model.dto.request.EventRequest;
import com.example.restapi_exception.model.dto.response.ApiResponse;
import com.example.restapi_exception.model.dto.response.EventResponse;
import com.example.restapi_exception.model.entity.Event;
import com.example.restapi_exception.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventResponse>>> getAllEvents() {

        List<Event> data = eventService.getAllEvents();

        ApiResponse<List<EventResponse>> response = ApiResponse.<List<EventResponse>>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Retrieved events successfully")
                .status(HttpStatus.OK.name())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{EventId}")
    public ResponseEntity<ApiResponse<EventResponse>> getById(@PathVariable Integer EventId) {

        ApiResponse<EventResponse> response = ApiResponse.<EventResponse>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Retrieved event successfully")
                .status(HttpStatus.OK.name())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody EventRequest request) {

        eventService.createEvent(request);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Event created successfully")
                .status(HttpStatus.CREATED.name())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{EventId}")
    public ResponseEntity<ApiResponse<Void>> deleteEventById(@PathVariable Integer EventId) {

        eventService.deleteEvent(EventId);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Event deleted successfully")
                .status(HttpStatus.OK.name())
                .build();

        return ResponseEntity.ok(response);
    }
}
