CREATE TABLE comment
(
    comment_id bigint   NOT NULL AUTO_INCREMENT COMMENT 'pk',
    board_id   bigint   NOT NULL COMMENT '댓글 작성게시물',
    user_id    bigint   NOT NULL COMMENT '댓글 작성자',
    comment    text     NOT NULL COMMENT '댓글 내용',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    PRIMARY KEY (comment_id)
) ENGINE = InnoDB COMMENT '댓글 정보';