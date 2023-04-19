CREATE TABLE badge
(
    badge_id      bigint      NOT NULL AUTO_INCREMENT COMMENT 'pk',
    name          varchar(20) NOT NULL COMMENT '뱃지명',
    image         varchar(255) NOT NULL COMMENT '뱃지 이미지',
    description   varchar(20) NOT NULL COMMENT '뱃지 설명',
    start_date    datetime COMMENT '이벤트용 뱃지 발급 시작 시간',
    end_date      datetime COMMENT '이벤트용 뱃지 발급 종료 시간',
    board_count   int COMMENT '조건-게시물 갯수',
    comment_count int COMMENT '조건-댓글 갯수',
    like_count    int COMMENT '조건-좋아요 갯수',
    created_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    updated_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    primary key (badge_id)
) ENGINE = InnoDB COMMENT '뱃지 정보';