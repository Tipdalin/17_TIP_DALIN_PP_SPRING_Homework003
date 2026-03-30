package com.example.restapi_exception.repository;

import com.example.restapi_exception.model.dto.request.VenueRequest;
import com.example.restapi_exception.model.dto.response.VenueResponse;
import com.example.restapi_exception.model.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Results(id = "VenueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
    })
    @Select("""
            SELECT * FROM venues
            LIMIT #{size}
            OFFSET (#{page} - 1 ) * #{size}
            """)
    List<VenueResponse> findAllVenues(Integer page, Integer size);

    @ResultMap("VenueMapper")
    @Select("SELECT * FROM venues WHERE venue_id = #{venueId}")
    Venue findVenueById(Integer venueId);

    @ResultMap("VenueMapper")
    @Select("""
            INSERT INTO venues(venue_name, location) 
            VALUES(#{venueName}, #{location})
            RETURNING *;
            """)
    void saveVenue(Venue venue);

    @ResultMap("VenueMapper")
    @Update("""
            UPDATE venues 
            SET venue_name=#{venue.venueName}, location=#{venue.location} 
            WHERE venue_id=#{venueId}
            RETURNING *
            """)
    void updateVenue(Integer venueId , @Param("venue") VenueRequest venueRequest);

    @ResultMap("VenueMapper")
    @Delete("DELETE FROM venues WHERE venue_id=#{venueId}")
    void deleteVenueById(Integer id);
}
