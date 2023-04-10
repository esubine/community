ALTER TABLE board ADD likes int NOT NULL default 0 COMMENT '게시물 좋아요 수';

ALTER TABLE board ADD reports int NOT NULL default 0 COMMENT '게시물 신고 수';