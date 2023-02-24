SELECT * FROM MEMBER;
SELECT * FROM BOARD;
SELECT * FROM REPLY;
SELECT * FROM BLIKE;
SELECT * FROM REPORT;

TRUNCATE BOARD; 
TRUNCATE MEMBER; 

/*
 * 회원 데이터
 */
INSERT INTO MEMBER
(M_ID, M_PW, M_NM, CAT_NM, PHONE_NO, `ROLE`, POST_NO, ADDRESS1, ADDRESS2)
VALUES('abc123', '1234', '김회원', '냥이', '01000000000', 'MEMBER', '01234', '서울 노원구 공릉로 10길 123', '1234호');

INSERT INTO MEMBER
(M_ID, M_PW, M_NM, CAT_NM, PHONE_NO, `ROLE`, POST_NO, ADDRESS1, ADDRESS2)
VALUES('hong', '1234', '홍길동', '동글이', '01011111111', 'MEMBER', '02222', '서울 종로구 300-22', '101호');

INSERT INTO MEMBER
(M_ID, M_PW, M_NM, CAT_NM, PHONE_NO, `ROLE`, POST_NO, ADDRESS1, ADDRESS2)
VALUES('kang', '1234', '강문영', '냥이', '01022222222', 'MEMBER', '03333', '서울 강남구 테헤란로 12길', null);

INSERT INTO MEMBER
(M_ID, M_PW, M_NM, CAT_NM, PHONE_NO, `ROLE`, POST_NO, ADDRESS1, ADDRESS2)
VALUES('kim', '1234', '김길동', '김냥이', '01033333333', 'MEMBER', '04444', '서울 송파구 백제고분로 123길', '109동 1004호');

INSERT INTO MEMBER
(M_ID, M_PW, M_NM, CAT_NM, PHONE_NO, `ROLE`, POST_NO, ADDRESS1, ADDRESS2)
VALUES('cho', '1234', '조수빈', '나만없다', '01044444444', 'MEMBER', '05555', '서울 노원구 공릉로 100길', '202호');

/*
 * 게시판 데이터
 */
INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(100, '안녕하세요', '처음뵙겠습니다.');

INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(100, '고양이 좀 보세요', '길에서 만났어요');

INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(100, 'this is title', 'this is content');

INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(101, '고양아 안녕!!', '너무 귀엽죠?');

INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(102, '귀여운 사진 보고가요', '길냥이인데 너무 귀여워요!!');

INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(102, '얘도 길냥이', '이전 글에 올린 애랑 형제인가봐요');

INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(103, '이거 올리려고 가입했습니다..', 'ㅋㅋㅋ 똥배 보세요');

INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(104, '저는 고양이가 없습니다.', '님들아 너무 자랑하지 마세요 빡치니까.');

INSERT INTO BOARD
(M_NO, B_TITLE, B_CONTENT)
VALUES(104, '길냥이ㅋ', '아무리 봐도 저한테 관심있는 냥이 같죠?');


/*
 * 댓글 데이터
 */
INSERT INTO REPLY
(B_NO, M_NO, PARENT_NO, RE_DATE, RE_CONTENT, RE_STEP)
VALUES(1, 101, null, curtime(), '안녕하세요 저도 어제 가입했어요.', 1);

INSERT INTO REPLY
(B_NO, M_NO, PARENT_NO, RE_DATE, RE_CONTENT, RE_STEP)
VALUES(1, 100, 1, curtime(), '반겨주시니 감사합니다!', 2);

INSERT INTO REPLY
(B_NO, M_NO, PARENT_NO, RE_DATE, RE_CONTENT, RE_STEP)
VALUES(2, 103, null, curtime(), '좀 귀엽네요!!!', 1);

INSERT INTO REPLY
(B_NO, M_NO, PARENT_NO, RE_DATE, RE_CONTENT, RE_STEP)
VALUES(3, 104, null, curtime(), 'ㅋ 빡치게 하지마셈;', 1);

INSERT INTO REPLY
(B_NO, M_NO, PARENT_NO, RE_DATE, RE_CONTENT, RE_STEP)
VALUES(3, 103, 4, curtime(), '님 왤케 시비 거세요.. 이 분 이상해요;; 신고해야겠어요.', 2);



/*
 * 좋아요 데이터
 */
INSERT INTO BLIKE (B_NO, M_NO) VALUES(2, 102);
INSERT INTO BLIKE (B_NO, M_NO) VALUES(5, 102);
INSERT INTO BLIKE (B_NO, M_NO) VALUES(7, 102);
INSERT INTO BLIKE (B_NO, M_NO) VALUES(4, 103);
INSERT INTO BLIKE (B_NO, M_NO) VALUES(7, 103);

/*
 * 신고 데이터
 * 		C : Board 게시글, 댓글 대댓글 신고
 * 		TARGET_NO 에는 게시글 PK 혹은 댓글 PK가 들어가야 함
 *		RP_STEP = 0(글), 1(댓글), 2(대댓글)
 */
INSERT INTO REPORT (TARGET_NO, RP_STEP, M_NO, RP_CONTENT)
VALUES(4, 1, 104, '시비성 댓글');

INSERT INTO REPORT (TARGET_NO, RP_STEP, M_NO, RP_CONTENT)
VALUES (4, 1, 104, '시비성 댓글');

INSERT INTO REPORT (TARGET_NO, RP_STEP, M_NO, RP_CONTENT)
VALUES (3, 0, 104, '시비성 댓글');
