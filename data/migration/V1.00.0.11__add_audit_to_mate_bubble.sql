ALTER TABLE mate_bubble
    ADD COLUMN create_at datetime default CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE mate_bubble
    ADD COLUMN update_at datetime;