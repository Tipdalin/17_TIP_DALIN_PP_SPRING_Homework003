CREATE DATABASE event;

CREATE TABLE venues(
                       venue_id SERIAL PRIMARY KEY,
                       venue_name VARCHAR(100) NOT NULL,
                       location VARCHAR(100) NOT NULL

);

CREATE TABLE events(
                       event_id SERIAL PRIMARY KEY,
                       event_name VARCHAR(100) NOT NULL,
                       event_date DATE NOT NULL,
                       venue_id INT REFERENCES venues(venue_id)
                           ON DELETE CASCADE
                           ON UPDATE CASCADE
                                               NOT NULL
);

CREATE TABLE attendees(
                          attendee_id SERIAL PRIMARY KEY ,
                          attendee_name VARCHAR(100) NOT NULL ,
                          email VARCHAR(100) NOT NULL
);

CREATE TABLE event_attendee(
                               attendee_id INT  REFERENCES attendees(attendee_id)
                                   ON DELETE CASCADE
                                   ON UPDATE CASCADE ,
                               event_id INT REFERENCES events(event_id)
                                   ON DELETE CASCADE
                                   ON UPDATE CASCADE
);

-- Insert data
INSERT INTO venues(venue_name, location)
VALUES ('Koh Pich','PP'),
       ('City Hall','Kandal'),
       ('Takeo Hall','Takeo');

INSERT INTO attendees(attendee_name, email)
VALUES ('rosie','rosie@gmail.com'),
       ('jennie','jennie@gmail.com');

INSERT INTO events(event_name, event_date, venue_id)
VALUES ('Wedding','2026-03-27',(SELECT venue_id FROM venues WHERE venue_name = 'Koh Pich')),
       ('Birthday','2026-03-30',(SELECT venue_id FROM venues WHERE venue_name = 'Takeo Hall'));

INSERT INTO event_attendee(attendee_id, event_id)
VALUES (
           (SELECT attendee_id FROM attendees WHERE attendee_name= 'rosie'),
           (SELECT event_id FROM events WHERE event_name= 'Wedding')
       );

