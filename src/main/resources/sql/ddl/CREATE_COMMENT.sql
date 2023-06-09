CREATE TABLE comment
(
    comment_id        bigint   NOT NULL AUTO_INCREMENT COMMENT 'pk',
    board_id          bigint   NOT NULL COMMENT '댓글 작성게시물',
    parent_comment_id bigint COMMENT '대댓글 부모 fk',
    user_id           bigint   NOT NULL COMMENT '댓글 작성자',
    comment           text     NOT NULL COMMENT '댓글 내용',
    is_delete         boolean  NOT NULL COMMENT '댓글 삭제 여부',
    created_at        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    PRIMARY KEY (comment_id)
) ENGINE = InnoDB COMMENT '댓글 정보';

