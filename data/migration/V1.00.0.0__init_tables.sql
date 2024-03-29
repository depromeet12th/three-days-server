CREATE TABLE `client`
(
    `client_id`          bigint        NOT NULL AUTO_INCREMENT,
    `member_id`          bigint        NOT NULL,
    `fcm_token`          varchar(1000) NOT NULL,
    `identification_key` varchar(1000) NOT NULL,
    PRIMARY KEY (`client_id`)
);


CREATE TABLE `global_notification`
(
    `global_notification_id` bigint       NOT NULL AUTO_INCREMENT,
    `contents`               varchar(100) DEFAULT NULL,
    `day_of_week`            varchar(255) NOT NULL,
    `notification_time`      time         NOT NULL,
    PRIMARY KEY (`global_notification_id`)
);


CREATE TABLE `member`
(
    `member_id`             bigint       NOT NULL AUTO_INCREMENT,
    `certification_id`      varchar(255) NOT NULL,
    `certification_subject` varchar(255) DEFAULT NULL,
    `name`                  varchar(255) DEFAULT NULL,
    `notification_consent`  bit(1)       DEFAULT NULL,
    `resource`              json         DEFAULT NULL,
    PRIMARY KEY (`member_id`)
);


CREATE TABLE `notification_history`
(
    `notification_history_id` bigint       NOT NULL AUTO_INCREMENT,
    `contents`                varchar(100) DEFAULT NULL,
    `member_id`               bigint       NOT NULL,
    `notification_id`         bigint       NOT NULL,
    `status`                  varchar(100) NOT NULL,
    `type`                    varchar(255) DEFAULT NULL,
    `create_at`               datetime(6)  NOT NULL,
    PRIMARY KEY (`notification_history_id`)
);


CREATE TABLE habit
(
    `habit_id`               bigint                                                                       NOT NULL AUTO_INCREMENT,
    `day_of_weeks`           SET ('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY') NOT NULL,
    `imoji_path`             varchar(255) DEFAULT NULL,
    `color`                  varchar(40)  DEFAULT NULL,
    `member_id`              bigint                                                                       NOT NULL,
    `title`                  varchar(255) DEFAULT NULL,
    `archive_number_of_date` int                                                                          NOT NULL,
    `status`                 varchar(255)                                                                 NOT NULL,
    `deleted`                bit(1)                                                                       NOT NULL,
    `create_at`              datetime(6)                                                                  NOT NULL,
    PRIMARY KEY (habit_id)
);

CREATE TABLE `mate`
(
    `mate_id`        bigint       NOT NULL AUTO_INCREMENT,
    `member_id`      bigint       NOT NULL,
    `habit_id`       bigint       NOT NULL,
    `character_type` varchar(255) NOT NULL,
    `level`          int          NOT NULL,
    `title`          varchar(255) DEFAULT NULL,
    `level_up_at`    datetime(6)  DEFAULT NULL,
    `create_at`      datetime(6)  NOT NULL,
    `deleted`        bit(1)       NOT NULL,
    PRIMARY KEY (`mate_id`)
);


CREATE TABLE habit_achievement
(
    `habit_achievement_id`  bigint NOT NULL AUTO_INCREMENT,
    `member_id`             bigint NOT NULL,
    `habit_id`              bigint NOT NULL,
    `achievement_date`      date   NOT NULL,
    `next_achievement_date` date   NOT NULL,
    `sequence`              int    NOT NULL,
    PRIMARY KEY (habit_achievement_id)
);


CREATE TABLE `habit_notification`
(
    `habit_notification_id` bigint       NOT NULL AUTO_INCREMENT,
    `habit_id`              bigint       NOT NULL,
    `member_id`             bigint       NOT NULL,
    `contents`              varchar(100) DEFAULT NULL,
    `day_of_week`           varchar(255) NOT NULL,
    `notification_time`     time         NOT NULL,
    PRIMARY KEY (`habit_notification_id`)
);


CREATE TABLE `reward_history`
(
    `reward_history_id` bigint      NOT NULL AUTO_INCREMENT,
    `member_id`         bigint      NOT NULL,
    `habit_id`          bigint      NOT NULL,
    `create_at`         datetime(6) NOT NULL,
    PRIMARY KEY (`reward_history_id`)
);
