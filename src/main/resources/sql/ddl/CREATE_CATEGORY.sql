CREATE TABLE category
(
    category_id   bigint      NOT NULL AUTO_INCREMENT COMMENT 'pk',
    category_name varchar(20) NOT NULL COMMENT '카테고리명',
    PRIMARY KEY (category_id)
) ENGINE = InnoDB COMMENT '게시판 카테고리';