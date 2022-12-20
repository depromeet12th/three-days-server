ALTER TABLE global_notification
    ADD COLUMN create_at datetime(6) NOT NULL;
ALTER TABLE global_notification
    ADD COLUMN update_at datetime(6);
