ALTER TABLE board ADD like_count int NOT NULL default 0 COMMENT '게시물 좋아요 수';

ALTER TABLE board ADD report_count int NOT NULL default 0 COMMENT '게시물 신고 수';

ALTER TABLE board ADD category_id bigint NOT NULL COMMENT '게시판 카테고리';