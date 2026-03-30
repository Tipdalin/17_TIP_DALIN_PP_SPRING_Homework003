package com.example.restapi_exception.controller;

import com.example.restapi_exception.model.dto.request.AttendeeRequest;
import com.example.restapi_exception.model.dto.response.ApiResponse;
import com.example.restapi_exception.model.dto.response.AttendeeResponse;
import com.example.restapi_exception.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AttendeeResponse>>> getAllAttendees() {

        List<AttendeeResponse> data = attendeeService.getAllAttendees();

        ApiResponse<List<AttendeeResponse>> response = ApiResponse.<List<AttendeeResponse>>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Retrieved all attendees successfully")
                .status(HttpStatus.OK.name())
                .payload(data)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<AttendeeResponse>> attendeeId(@PathVariable Integer attendeeId) {

        AttendeeResponse data = attendeeService.getAttendeeById(attendeeId);

        ApiResponse<AttendeeResponse> response = ApiResponse.<AttendeeResponse>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Retrieved attendee " + attendeeId + " successfully")
                .status(HttpStatus.OK.name())
                .payload(data)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createAttendee(@RequestBody AttendeeRequest request) {

        attendeeService.createAttendee(request);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Attendee created successfully")
                .status(HttpStatus.CREATED.name())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Void>> updateAttendeeById(@PathVariable Integer attendeeId,
                                                    @RequestBody AttendeeRequest request) {

        attendeeService.updateAttendeeById(attendeeId,request);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Attendee updated successfully")
                .status(HttpStatus.OK.name())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Void>> deleteAttendeeById(@PathVariable Integer attendeeId) {

        attendeeService.deleteAttendeeById(attendeeId);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Attendee deleted successfully")
                .status(HttpStatus.OK.name())
                .build();

        return ResponseEntity.ok(response);
    }
}
