package com.example.restapi_exception.repository;

import com.example.restapi_exception.model.dto.response.AttendeeResponse;
import com.example.restapi_exception.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
    public interface AttendeeRepository {

        @Select("""
        SELECT * FROM attendees
        """)
        @Results(id = "attendeeMapper", value = {
                @Result(property = "attendeeId", column = "attendee_id"),
                @Result(property = "attendeeName", column = "attendee_name"),
                @Result(property = "email", column = "email"),
                @Result(property = "eventId", column = "event_id")
        })
        List<AttendeeResponse> findAllAttendees();

        @Select("""
        SELECT * FROM attendees 
        WHERE attendee_id = #{attendeeId}
        """)
        @ResultMap("attendeeMapper")
        AttendeeResponse findAttendeeById();

        @Insert("""
        INSERT INTO attendees(attendee_name, email, event_id)
        VALUES(#{attendeeName}, #{email}, #{eventId})
        """)
        void saveAttendee(Attendee attendee);

        @Update("""
        UPDATE attendees
        SET attendee_name = #{attendeeName},
            email = #{email},
            event_id = #{eventId}
        WHERE attendee_id = #{attendeeId}
    """)
        void updateAttendeeById();

        @Delete("""
        DELETE FROM attendees WHERE attendee_id = #{attendeeId}
    """)
        void deleteAttendeeById(Integer attendeeId);
    }

