CREATE TABLE board
(
    board_id     bigint      NOT NULL AUTO_INCREMENT COMMENT 'pk',
    title        varchar(20) NOT NULL COMMENT '게시물 제목',
    contents     text        NOT NULL COMMENT '게시물 내용',
    user_id      bigint      NOT NULL COMMENT '게시물 작성자',
    like_count   int         NOT NULL DEFAULT 0 COMMENT '좋아요 수',
    report_count int         NOT NULL DEFAULT 0 COMMENT '게시물 신고 수',
    delete_check boolean     NOT NULL COMMENT '삭제 여부',
    created_at   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    updated_at   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    PRIMARY KEY (board_id)
) ENGINE = InnoDB COMMENT '게시판 정보';

