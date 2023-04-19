INSERT INTO badge (name, image, description, board_count, comment_count, like_count)
VALUES ('게시물 1개', 'abc', '게시물 1개', 1, null, null),
       ('게시물 3개', 'abc', '게시물 3개', 3, null, null),
       ('게시물 5개', 'abc', '게시물 5개', 5, null, null),
       ('댓글 1개', 'abc', '댓글 1개', null, 1, null),
       ('댓글 3개', 'abc', '댓글 3개', null, 3, null),
       ('댓글 5개', 'abc', '댓글 5개', null, 5, null)
;

INSERT INTO badge (name, image, description, board_count, comment_count, like_count, start_date, end_date)
VALUES ('이벤트 기간', 'abc', '이벤트 기간', null, null, 1, '2020-01-01', '2099-01-01'),
       ('이벤트 기간 아님', 'abc', '이벤트 기간 아님', null, null, 1, '2002-01-01', '2020-01-01')
;