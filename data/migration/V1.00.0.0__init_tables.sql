CREATE TABLE `client` (
                          `client_id` bigint NOT NULL AUTO_INCREMENT,
                          `fcm_token` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `member_id` bigint NOT NULL,
                          PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `global_notification` (
                                       `global_notification_id` bigint NOT NULL AUTO_INCREMENT,
                                       `contents` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                       `day_of_week` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                       `notification_time` time NOT NULL,
                                       PRIMARY KEY (`global_notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `member` (
                          `member_id` bigint NOT NULL AUTO_INCREMENT,
                          `certification_subject` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `notification_consent` bit(1) DEFAULT NULL,
                          `profile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `notification_history` (
                                        `notification_history_id` bigint NOT NULL AUTO_INCREMENT,
                                        `contents` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        `create_date` datetime(6) NOT NULL,
                                        `member_id` time NOT NULL,
                                        `notification_id` bigint NOT NULL,
                                        `status` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                        PRIMARY KEY (`notification_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `objective` (
                             `objective_id` bigint NOT NULL AUTO_INCREMENT,
                             `character_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `create_date` datetime(6) NOT NULL,
                             `day_of_weeks` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `deleted` bit(1) NOT NULL,
                             `imoji_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `level` int NOT NULL,
                             `member_id` bigint NOT NULL,
                             `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             PRIMARY KEY (`objective_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `objective_achievement` (
                                         `objective_achievement_id` bigint NOT NULL AUTO_INCREMENT,
                                         `achievement_date` date NOT NULL,
                                         `member_id` bigint NOT NULL,
                                         `next_achievement_date` date NOT NULL,
                                         `objective_id` bigint NOT NULL,
                                         `sequence` int NOT NULL,
                                         PRIMARY KEY (`objective_achievement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `objective_notification` (
                                          `objective_notification_id` bigint NOT NULL AUTO_INCREMENT,
                                          `contents` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                          `day_of_week` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                          `member_id` bigint NOT NULL,
                                          `notification_time` datetime(6) NOT NULL,
                                          `objective_id` bigint NOT NULL,
                                          PRIMARY KEY (`objective_notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `reward_history` (
                                  `reward_history_id` bigint NOT NULL AUTO_INCREMENT,
                                  `create_date` datetime(6) NOT NULL,
                                  `member_id` bigint NOT NULL,
                                  `objective_id` bigint NOT NULL,
                                  PRIMARY KEY (`reward_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
