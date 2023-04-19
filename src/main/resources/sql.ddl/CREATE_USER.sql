CREATE TABLE user
(
    user_id        bigint       NOT NULL AUTO_INCREMENT COMMENT 'pk',
    real_name      varchar(20)  NOT NULL COMMENT '사용자 실명',
    nickname       varchar(10)  NOT NULL COMMENT '사용자 닉네임',
    login_id       varchar(20)  NOT NULL COMMENT '로그인 아이디',
    login_password varchar(255) NOT NULL COMMENT '로그인 비밀번호',
    is_delete      boolean      NOT NULL COMMENT '유저 삭제 여부',
    created_at     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    PRIMARY KEY (user_id)
) ENGINE = InnoDB COMMENT '사용자 정보';