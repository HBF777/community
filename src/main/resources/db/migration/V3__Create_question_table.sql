create table question
(
    id            INT auto_increment,
    title         VARCHAR(50),
    description   TEXT,
    gmt_create    BIGINT,
    gmt_modified  BIGINT,
    creator       INT,
    comment_count INT default 8,
    view_count    INT default 8,
    like_count    INT default 0,
    tag           VARCHAR(255),
    constraint QUESTION_PK
        primary key (id)
);

