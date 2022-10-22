CREATE
USER 'three-days-local'@'localhost' IDENTIFIED BY 'three-days-local';
CREATE
USER 'three-days-local'@'%' IDENTIFIED BY 'three-days-local';

GRANT ALL PRIVILEGES ON *.* TO
'three-days-local'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO
'three-days-local'@'%';

CREATE
DATABASE threedays DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE
DATABASE batch DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- THREEDAYS.MEMBER
DROP TABLE IF EXISTS threedays.member;
CREATE TABLE threedays.member (
                                  `member_id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                  `name`	varchar(255)	NULL,
                                  `certification_subject`	varchar(10)	NULL,
                                  `certification_id`	VARCHAR(255)	NULL,
                                  `profile`	VARCHAR(255)	NULL,
                                  `fcm_token`	VARCHAR(255)	NULL,
                                  `achievment`	VARCHAR(255)	NULL
);

-- THREEDAYS.OBJECTIVE
DROP TABLE IF EXISTS threedays.objective;
CREATE TABLE threedays.objective (
                                     `objective_id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     `title`	varchar(40)	NULL,
                                     `start_date`	timestamp	NULL,
                                     `end_date`	timestamp	NULL,
                                     `start_time`	timestamp	NULL,
                                     `end_time`	timestamp	NULL,
                                     `status`	timestamp	NULL,
                                     `created_date`	timestamp	NULL,
                                     `sequence`	int	NULL,
                                     `last_achievement_date`	timestamp	NULL,
                                     `total_achievement_count`	int	NULL,
                                     `member_id`	bigint	NOT NULL
);
ALTER TABLE threedays.objective ADD CONSTRAINT FOREIGN KEY (member_id)
    REFERENCES member (member_id) ON DELETE CASCADE;

-- THREEDAYS.OBJECTIVE_HISTORY
DROP TABLE IF EXISTS threedays.objective_history;
CREATE TABLE threedays.objective_history (
                                             `objective_history_id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                             `achievement_date`	timestamp	NULL,
                                             `title`	VARCHAR(255)	NULL,
                                             `objective_id`	bigint	NOT NULL
);
ALTER TABLE threedays.objective_history ADD CONSTRAINT FOREIGN KEY (objective_id)
    REFERENCES objective (objective_id) ON DELETE CASCADE;

-- THREEDAYS.NOTIFICATION
DROP TABLE IF EXISTS threedays.notification;
CREATE TABLE threedays.notification (
                                        `notification_id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        `notification_time`	timestamp	NULL,
                                        `contents`	varchar(100)	NULL,
                                        `objective_id`	bigint	NOT NULL
);
