CREATE TABLE `reward_achievement`
(
    `reward_achievement_id` bigint      NOT NULL AUTO_INCREMENT,
    `reward_id`                         varchar(100) DEFAULT NULL,
    `habit_achievement_id`              varchar(255) NOT NULL,
    `create_at`                         datetime(6) NOT NULL,
    `update_at`                         datetime(6)
    PRIMARY KEY (`reward_achievement_id`)
);