CREATE TABLE token
(
    token_id   bigint      NOT NULL AUTO_INCREMENT COMMENT 'pk',
    token      varchar(100) NOT NULL COMMENT '토큰',
    user_id    bigint NOT NULL COMMENT '사용자 고유번호',
    is_delete boolean NOT NULL COMMENT '토큰 삭제 여부',
    created_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    PRIMARY KEY (token_id)
) ENGINE = InnoDB COMMENT '토큰 정보';