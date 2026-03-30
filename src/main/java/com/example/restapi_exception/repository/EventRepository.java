package com.example.restapi_exception.repository;

import com.example.restapi_exception.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("""
        SELECT * FROM events
    """)
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venueId", column = "venue_id")
    })
    List<Event> findAllEvents();

    @Select("""
        SELECT * FROM events 
        WHERE event_id = #{eventId}
    """)
    @ResultMap("eventMapper")
    Event findEventById(Integer eventId);

    @Insert("""
        INSERT INTO events(event_name, event_date, venue_id)
        VALUES(#{eventName}, #{eventDate}, #{venueId})
    """)
    void saveEvent(Event event);

    @Update("""
        UPDATE events
        SET event_name = #{eventName},
            event_date = #{eventDate},
            venue_id = #{venueId}
        WHERE event_id = #{eventId}
    """)
    void updateEventById(Event event);

    @Delete("""
        DELETE FROM events WHERE event_id = #{eventId}
    """)
    void deleteEventById(Integer eventId);
}
