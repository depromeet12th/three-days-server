-- template Table Create SQL
CREATE TABLE member
(
    `member_id`           BIGINT       NOT NULL AUTO_INCREMENT COMMENT '멤버 아이디',
    `nickname`                  VARCHAR(200) NOT NULL COMMENT '닉네임',
    PRIMARY KEY (member_id)
);

ALTER TABLE member COMMENT '샘플';
