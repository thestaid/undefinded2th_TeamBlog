//게시판 SQ문
CREATE TABLE member_board(
num NUMBER PRIMARY KEY,
writer VARCHAR2(100) NOT NULL,
title VARCHAR2(100) NOT NULL,
content CLOB,
viewCount NUMBER,
regdate DATE
);

CREATE SEQUENCE member_board_seq NOCACHE;

insert into member_board 
values (member_board_seq.nextval,'작성자1', '제목1', '내용1', 0, sysdate);


//방명록 SQL문

CREATE SEQUENCE visitor_seq NOCACHE;

CREATE TABLE visitor_comment(
num NUMBER PRIMARY KEY,--덧글의 글번호
writer VARCHAR2(100),
content CLOB,
regdate DATE
);

//게시판 덧글 SQL문

CREATE SEQUENCE board_comment_seq NOCACHE;

CREATE TABLE board_comment(
num NUMBER PRIMARY KEY,--덧글의 글번호
writer VARCHAR2(100),
content VARCHAR2(500),
target_id VARCHAR2(100), --덧글의 대상이 되는 되는 아이디
ref_group NUMBER,
comment_group NUMBER,
regdate DATE
);

//파일시스템 SQL문

CREATE TABLE board_data
(num NUMBER PRIMARY KEY,
writer VARCHAR2(100) NOT NULL,
title VARCHAR2(100) NOT NULL,
orgFileName VARCHAR2(100) NOT NULL,
saveFileName VARCHAR2(100) NOT NULL,
fileSize NUMBER NOT NULL,
regdate DATE);

CREATE SEQUENCE board_data_seq NOCACHE;