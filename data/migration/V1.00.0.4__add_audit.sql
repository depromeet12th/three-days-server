ALTER TABLE client
    ADD COLUMN create_at datetime(6) NOT NULL;
ALTER TABLE client
    ADD COLUMN update_at datetime(6);

ALTER TABLE habit
    ADD COLUMN update_at datetime(6);

ALTER TABLE habit_achievement
    ADD COLUMN create_at datetime(6) NOT NULL;
ALTER TABLE habit_achievement
    ADD COLUMN update_at datetime(6);

ALTER TABLE habit_notification
    ADD COLUMN create_at datetime(6) NOT NULL;
ALTER TABLE habit_notification
    ADD COLUMN update_at datetime(6);

ALTER TABLE mate
    ADD COLUMN update_at datetime(6);

ALTER TABLE member
    ADD COLUMN update_at datetime(6);

ALTER TABLE notification_history
    ADD COLUMN update_at datetime(6);

ALTER TABLE reward_history
    ADD COLUMN update_at datetime(6);
