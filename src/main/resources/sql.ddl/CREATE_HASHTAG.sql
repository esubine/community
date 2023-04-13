CREATE TABLE hashtag
(
    hashtag_id bigint      NOT NULL AUTO_INCREMENT COMMENT 'pk',
    name       varchar(20) NOT NULL COMMENT '해시태그 내용',
    created_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    updated_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    primary key (hashtag_id)
) ENGINE = InnoDB COMMENT '해시태그';

CREATE TABLE board_hashtag
(
    board_hashtag_id bigint   NOT NULL AUTO_INCREMENT COMMENT 'pk',
    board_id         bigint   NOT NULL COMMENT 'board fk',
    hashtag_id       bigint   NOT NULL COMMENT 'hashtag fk',
    created_at       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    updated_at       datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    primary key (board_hashtag_id)
) ENGINE = InnoDB COMMENT '보드-해시태그 M:N';