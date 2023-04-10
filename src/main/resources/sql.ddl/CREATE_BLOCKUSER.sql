CREATE TABLE block_user
    (
    block_user_id bigint NOT NULL AUTO_INCREMENT COMMENT 'pk',
    requester_id bigint NOT NULL COMMENT '차단 요청한 유저',
    target_id  bigint NOT NULL COMMENT '차단 당한 유저',
    PRIMARY KEY (block_user_id)
) ENGINE = InnoDB COMMENT '유저 차단';