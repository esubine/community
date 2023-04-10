CREATE TABLE board_report_info
(
    board_report_info_id  bigint NOT NULL AUTO_INCREMENT COMMENT 'pk',
    user_id  bigint NOT NULL COMMENT '신고한 유저',
    board_id bigint NOT NULL COMMENT '신고된 게시물',
    PRIMARY KEY (board_report_info_id)
) ENGINE = InnoDB COMMENT '게시물 신고 정보';