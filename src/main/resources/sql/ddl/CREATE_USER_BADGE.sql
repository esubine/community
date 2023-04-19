CREATE TABLE user_badge
(
    user_badge_id bigint   NOT NULL AUTO_INCREMENT COMMENT 'pk',
    user_id         bigint   NOT NULL COMMENT 'user fk',
    badge_id       bigint   NOT NULL COMMENT 'badge fk',
    created_at       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    updated_at       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    PRIMARY KEY (user_badge_id)
)ENGINE = InnoDB COMMENT '유저-뱃지 M:N';