CREATE TABLE event_history (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type varchar(20) NOT NULL,
  registration_id varchar(255),
  created_at timestamp DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE event_history
    ADD CONSTRAINT fk_event_history_registrations
         FOREIGN KEY (registration_id) REFERENCES registration(id);