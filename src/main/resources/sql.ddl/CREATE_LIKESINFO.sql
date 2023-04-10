CREATE TABLE board_like_info
(
    like_id  bigint NOT NULL AUTO_INCREMENT COMMENT 'pk',
    user_id  bigint NOT NULL COMMENT '좋아요 누른 유저',
    board_id bigint NOT NULL COMMENT '좋아요 누른 게시판',
    PRIMARY KEY (like_id),
    UNIQUE KEY unique_boardid_userid (board_id, user_id)
) ENGINE = InnoDB COMMENT '게시판 좋아요 정보';