/*
SQLPLUS system/dba;

CREATE USER onion IDENTIFIED BY a123
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP;

GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE TO onion;

CONNECT onion/a123;
*/

DROP TABLE notice_image PURGE;
DROP TABLE notice PURGE;
DROP TABLE answer PURGE;
DROP TABLE inquiry PURGE;
DROP TABLE review_image PURGE;
DROP TABLE review PURGE;
DROP TABLE complain PURGE;

DROP TABLE delivery PURGE;
DROP TABLE orders PURGE;
DROP TABLE wish PURGE;

DROP TABLE chat PURGE;
DROP TABLE chatroom PURGE;

DROP TABLE bidding PURGE;
DROP TABLE product_image PURGE;
DROP TABLE product PURGE;

DROP TABLE category PURGE;

DROP TABLE town PURGE;
DROP TABLE coordinate PURGE;

DROP TABLE search PURGE;
DROP TABLE keyword PURGE;
DROP TABLE prohibition_keyword PURGE;
DROP TABLE follow PURGE;
DROP TABLE block PURGE;
DROP TABLE member PURGE;


CREATE TABLE member (
	member_id			NUMBER			NOT NULL,
	role				VARCHAR2(50)    DEFAULT 'USER',
	user_id				VARCHAR2(30)    NOT NULL,
	pwd					VARCHAR2(60)    NOT NULL,
	nickname			VARCHAR2(40)    NOT NULL,
	name				VARCHAR2(30)    NOT NULL,
	birth				DATE		    NOT NULL,
	tel					CHAR(11)	    NOT NULL,
	postcode			CHAR(5)	        NOT NULL,
	address				VARCHAR2(100)	NOT NULL,
	detail_address		VARCHAR2(100)   NOT NULL,
	extra_address		VARCHAR2(100)   NULL,
	email				VARCHAR2(50)	NOT NULL,
	member_image_name	VARCHAR2(255)   NULL,
	cash	            NUMBER			DEFAULT 0,
	point	            NUMBER	        DEFAULT 0,
	user_grade          NUMBER          DEFAULT 0,
	complaint_count     NUMBER          DEFAULT 0,
	CONSTRAINT PK_MEMBER PRIMARY KEY (member_id)
);

CREATE TABLE follow (
	follow_id           NUMBER          NOT NULL,
	member_id           NUMBER          NOT NULL,
	follow_target_id    NUMBER          NOT NULL,
	CONSTRAINT PK_FOLLOW PRIMARY KEY (follow_id),
	CONSTRAINT FK_FOLLOW_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_FOLLOW_FOLLOW_TARGET_ID FOREIGN KEY (follow_target_id) REFERENCES member(member_id)
	);

CREATE TABLE block (
	block_id            NUMBER			NOT NULL,
	member_id       	NUMBER  		NOT NULL,
	block_target_id   	NUMBER  		NOT NULL,
	CONSTRAINT PK_BLOCK PRIMARY KEY (block_id),
	CONSTRAINT FK_BLOCK_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_BLOCK_BLOCK_TARGET_ID FOREIGN KEY (block_target_id) REFERENCES member(member_id)
);

CREATE TABLE keyword (
    keyword_id      	NUMBER          NOT NULL,
    member_id       	NUMBER          NOT NULL,
    keyword_name    	VARCHAR2(255)   NULL,
    CONSTRAINT PK_KEYWORD PRIMARY KEY (keyword_id),
    CONSTRAINT FK_KEYWORD_MEMBER_ID FOREIGN KEY (member_id) REFERENCES  member(member_id)
);

CREATE TABLE prohibition_keyword (
    prohibition_keyword_id      	NUMBER          NOT NULL,
    prohibition_keyword_name    	VARCHAR2(255)   NULL,
    CONSTRAINT PK_PROHIBITION_KEYWORD  PRIMARY KEY (prohibition_keyword_id)
);

CREATE TABLE search (
    search_id            NUMBER          NOT NULL,
    search_name          VARCHAR2(255)   NULL,
    search_count         NUMBER          NULL,
    CONSTRAINT PK_SEARCH PRIMARY KEY (search_id)
);


CREATE TABLE coordinate (
	coordinate_id 		NUMBER        	NOT NULL,
	town_name     		VARCHAR2(50)  	NOT NULL,
    latitude      		VARCHAR2(50)  	NOT NULL,
	longitude     		VARCHAR2(50)  	NOT NULL,
	CONSTRAINT PK_COORDINATE PRIMARY KEY (coordinate_id)
);

CREATE TABLE town (
    town_id       NUMBER NOT NULL,
    member_id     NUMBER NOT NULL,
    coordinate_id NUMBER NOT NULL,
    CONSTRAINT PK_TOWN PRIMARY KEY (town_id),
    CONSTRAINT FK_TOWN_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (member_id),
    CONSTRAINT FK_TOWN_COORDINATE_ID FOREIGN KEY (coordinate_id) REFERENCES coordinate (coordinate_id)
);

CREATE TABLE category (
    category_id     	NUMBER          NOT NULL,
    category_name  	    VARCHAR2(255)   NOT NULL,
    parent_id       	NUMBER          NULL,
    CONSTRAINT PK_CATEGORY PRIMARY KEY (category_id),
    CONSTRAINT FK_CATEGORY_PARENT_ID FOREIGN KEY (parent_id) REFERENCES category(category_id)
);

CREATE TABLE product (
	product_id          NUMBER          NOT NULL,
	member_id           NUMBER          NOT NULL,
	town_id             NUMBER          NOT NULL,
	category_id         NUMBER          NOT NULL,
	subject             VARCHAR2(255)   NULL,
	content             VARCHAR2(255)   NULL,
	price               NUMBER          NULL,
    representative_image VARCHAR2(255)  NULL,
	upload_date         DATE            DEFAULT SYSDATE,
	update_date         DATE            DEFAULT NULL,
	auction_deadline    DATE            NULL,
	view_count          NUMBER          DEFAULT 0,
	product_progress    VARCHAR2(20)    NULL,
	pay_status          VARCHAR2(20)    NULL,
	blind_status        CHAR(1)         DEFAULT 0,
	CONSTRAINT PK_PRODUCT PRIMARY KEY (product_id),
	CONSTRAINT FK_PRODUCT_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_PRODUCT_TOWN_ID FOREIGN KEY (town_id) REFERENCES town(town_id),
    CONSTRAINT FK_PRODUCT_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES  category(category_id)
);

CREATE TABLE product_image (
	product_image_id   	NUMBER       	NOT NULL,
	product_id         	NUMBER       	NOT NULL,
	product_image_name 	VARCHAR2(255) 	NOT NULL,
	CONSTRAINT PK_PRODUCT_IMAGE PRIMARY KEY (product_image_id),
	CONSTRAINT FK_PRODUCT_IMAGE_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE bidding (
    bidding_id          NUMBER          NOT NULL,
    product_id          NUMBER          NOT NULL,
    member_id           NUMBER          NOT NULL,
    bid                 NUMBER          NULL,
    bidding_time        DATE            NULL,
    CONSTRAINT PK_BIDDING PRIMARY KEY (bidding_id),
    CONSTRAINT FK_BIDDING_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id),
    CONSTRAINT FK_BIDDING_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE chatroom (
	chatroom_id     	NUMBER          NOT NULL,
	product_id      	NUMBER          NOT NULL,
	member_id       	NUMBER          NOT NULL,
	state           	VARCHAR2(10)    NOT NULL,
	create_date     	DATE            DEFAULT SYSDATE,
	modify_date     	DATE            DEFAULT NULL,
	CONSTRAINT PK_CHATROOM PRIMARY KEY (chatroom_id),
	CONSTRAINT FK_CHATROOM_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_CHATROOM_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE chat (
    chat_id         	NUMBER          NOT NULL,
    chatroom_id     	NUMBER          NOT NULL,
    member_id     	    NUMBER          NOT NULL,
    message         	VARCHAR2(600)   NULL,
    chat_image_name 	VARCHAR2(255)   NULL,
    read      	        CHAR(1)         DEFAULT 0,
    sending_time    	DATE            DEFAULT SYSDATE,
    CONSTRAINT PK_CHAT PRIMARY KEY (chat_id),
    CONSTRAINT FK_CHAT_CHATROOM_ID  FOREIGN KEY (chatroom_id) REFERENCES chatroom(chatroom_id),
    CONSTRAINT FK_CHAT_MEMBER_ID  FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE wish (
	wish_id         	NUMBER  		NOT NULL,
	member_id       	NUMBER  		NOT NULL,
	product_id      	NUMBER  		NOT NULL,
	CONSTRAINT PK_WISH PRIMARY KEY (wish_id),
	CONSTRAINT FK_WISH_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_WISH_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE orders (
    order_id	    	NUMBER	        NOT NULL,
    member_id	    	NUMBER          NOT NULL,
    product_id	    	NUMBER          NOT NULL,
    order_num           CHAR(15)        NOT NULL,
    imp_uid             VARCHAR2(20)    NULL,
    order_payment       NUMBER          NOT NULL,
    use_point           NUMBER          DEFAULT 0,
    order_state     	VARCHAR2(10)    DEFAULT 'ORDER',
    order_date      	DATE            DEFAULT SYSDATE,
    modified_date   	DATE            DEFAULT NULL,
    CONSTRAINT PK_ORDERS PRIMARY KEY (order_id),
    CONSTRAINT FK_ORDERS_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_ORDERS_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE delivery (
	order_id	    	NUMBER          NOT NULL,
    recipient           VARCHAR2(30)    NOT NULL,
    delivery_tel        CHAR(11)        NOT NULL,
	postcode	    	CHAR(5)	        NOT NULL,
	address         	VARCHAR2(255)   NOT NULL,
	detail_address  	VARCHAR2(255)   NOT NULL,
	extra_address   	VARCHAR2(255)   NULL,
	delivery_cost   	NUMBER          NOT NULL,
	request         	VARCHAR2(255)   NULL,
	CONSTRAINT PK_DELIVERY PRIMARY KEY (order_id),
	CONSTRAINT FK_DELIVERY_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE complain (
	complain_id         NUMBER          NOT NULL,
	member_id       	NUMBER          NOT NULL,
	product_id          NUMBER          NULL,
	complain_type       VARCHAR2(100)	NOT NULL,
	complain_date       DATE            DEFAULT SYSDATE,
	complain_content    VARCHAR2(255)	NOT NULL,
	status              VARCHAR2(20)	DEFAULT 'wait',
	CONSTRAINT PK_COMPLAIN PRIMARY KEY (complain_id),
	CONSTRAINT FK_COMPLAIN_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_COMPLAIN_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE review (
	review_id	        NUMBER	        NOT NULL,
	order_id	        NUMBER	        NOT NULL,
    member_id       	NUMBER          NOT NULL,
	review_content	    VARCHAR2(255)	NOT NULL,
	grade	            NUMBER          NOT NULL,
	review_date	        DATE            DEFAULT SYSDATE,
	CONSTRAINT PK_REVIEW PRIMARY KEY (review_id),
	CONSTRAINT FK_REVIEW_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(order_id),
    CONSTRAINT FK_REVIEW_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);
CREATE TABLE review_image(
    review_image_id     NUMBER          NOT NULL,
    review_id           NUMBER          NOT NULL,
    store_image_name    VARCHAR2(255)   NOT NULL,
    CONSTRAINT PK_REVIEW_IMAGE PRIMARY KEY (review_image_id),
    CONSTRAINT FK_REVIEW_IMAGE_REVIEW_ID FOREIGN KEY (review_id) REFERENCES review(review_id)
);

CREATE TABLE inquiry (
	inquiry_id	    	NUMBER	        NOT NULL,
	member_id	    	NUMBER	        NOT NULL,
	inquiry_type        VARCHAR2(50)	NOT NULL,
	detail_type         VARCHAR2(50)	NOT NULL,
	inquiry_subject     VARCHAR2(500)	NOT NULL,
	inquiry_content     VARCHAR2(500)	NOT NULL,
	inquiry_date    	DATE            DEFAULT SYSDATE,
	status          	VARCHAR2(20)	DEFAULT 'wait',
	secret              CHAR(1)         CONSTRAINT review_image_secret_CK check ( secret = '0' or secret = '1'),
	CONSTRAINT PK_INQUIRY PRIMARY KEY (inquiry_id),
	CONSTRAINT FK_INQUIRY_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE answer (
	answer_id	    	NUMBER	        NOT NULL,
	inquiry_id	    	NUMBER	        NOT NULL,
	member_id	    	NUMBER	        NOT NULL,
	answer_content      VARCHAR2(1000)	NOT NULL,
	answer_date     	DATE            DEFAULT SYSDATE,
	CONSTRAINT PK_ANSWER PRIMARY KEY (answer_id),
	CONSTRAINT FK_ANSWER_INQUIRY_ID FOREIGN KEY (inquiry_id) REFERENCES inquiry(inquiry_id),
	CONSTRAINT FK_ANSWER_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE notice (
	notice_id        	NUMBER          NOT NULL,
	member_id        	NUMBER          NOT NULL,
	notice_type      	VARCHAR2(30)    NOT NULL,
	notice_subject   	VARCHAR2(255)   NOT NULL,
	notice_content   	VARCHAR2(4000)  NOT NULL,
	notice_date      	DATE            DEFAULT SYSDATE,
	hit_count        	NUMBER          DEFAULT 0,
	CONSTRAINT PK_NOTICE_BOARD PRIMARY KEY (notice_id),
	CONSTRAINT FK_NOTICE_BOARD_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE notice_image (
	notice_image_id     NUMBER          NOT NULL,
	notice_id           NUMBER          NOT NULL,
	notice_image_name   VARCHAR2(255)   NOT NULL,
	CONSTRAINT PK_NOTICE_IMAGE PRIMARY KEY (notice_image_id),
	CONSTRAINT FK_NOTICE_IMAGE_NOTICE_ID FOREIGN KEY (notice_id) REFERENCES notice(notice_id)
);

----------------------------------------------------------------------------------------------------

INSERT INTO member VALUES(0, 'ADMIN', 'admin0', '$2a$10$8gkJSCifAA3aWUAZJazhnuYLi2JVSbC7D2UtDmu0cUG9NScriVtZ6', --pwd : admin123
                          'onion', '양파 마켓', SYSDATE, '01012341234', '06234', '서울 강남구 역삼동 823',
                          '4층 아이티윌', '', 'onion@gmail.com', 'onion.png', 1000000, 10000, 0, 0);
INSERT INTO member VALUES(1, 'ADMIN', 'admin', '$2a$10$8gkJSCifAA3aWUAZJazhnuYLi2JVSbC7D2UtDmu0cUG9NScriVtZ6', --pwd : admin123
                          'admin', '관리자', SYSDATE, '01012341234', '06234', '서울 강남구 역삼동 823',
                          '4층 아이티윌', '', 'youprice.onion.email@gmail.com', 'null.png', 1000000, 10000, 0, 0);
INSERT INTO member VALUES(2, 'USER', 'user1', '$2a$10$X5ICHy3CCqtWl0su87UzMuCe.v2V92TBIH5szmZ.hBJd/tD/7o8LW', --pwd : user1
                          '디굴디굴', '김디굴', SYSDATE, '01011111111', '06253', '서울 강남구 역삼동 823',
                          '4층', '', 'user1@naver.com', 'null.png', 200000, 10000, 0, 0);
INSERT INTO member VALUES(3, 'USER', 'user2', '$2a$10$yCBZmjwdiIsWneGJll69X.Z7DGnGx4pSgeBw1oNVZkbxvg4w./uUy', --pwd : user2
                          'user2', '유저2', SYSDATE, '01022222222', '06120', '서울 강남구 논현동 200-7',
                          '2', '', 'user2@naver.com', 'null.png', 20000, 1000, 0, 0);
INSERT INTO member VALUES(4, 'USER', 'user3', '$2a$10$Az6LE39WK9WzZB5eTstyN.a3Ry8eEJaOS.bwdNAr2J8pwlKjfRL.C', --pwd : user3
                          'user3', '유저3', SYSDATE, '01033333333', '06308', '서울 강남구 개포동 1205-1',
                          '3', '', 'user3@naver.com', 'null.png', 0, 0, 0, 0);
INSERT INTO member VALUES(5, 'USER', 'user4', '$2a$10$maqcisSSo/UvHjaBGlkdGeMdVmkYVVBUPtg9Yjy4eRXXV17v4yhTO', --pwd : user4
                          'user4', '유저4', SYSDATE, '01044444444', '06517', '서울 서초구 잠원동 54-5',
                          '4', '', 'user4@naver.com', 'null.png', 1000, 0, 0, 0);
INSERT INTO member VALUES(6, 'USER', 'user5', '$2a$10$No.Tv7ToHFb7dT0shjJlQ.acUoZeiW5a.PVvxn9fBL0ZjKcQ07Ipm', --pwd : user5
                          'user5', '유저5', SYSDATE, '01055555555', '06744', '서울 서초구 양재동 20-30',
                          '5', '', 'user5@naver.com', 'null.png', 2000, 0, 0, 0);
INSERT INTO member VALUES(7, 'USER', 'user6', '$2a$10$dTp.BTXTF4tkmWxzcKA0o.6QitOj1./MDoHfCFOfhgrQsJv3YvIgC', --pwd : user6
                          'user6', '유저6', SYSDATE, '01066666666', '06729', '서울 서초구 서초동 1357-35',
                          '6', '', 'user6@naver.com', 'null.png', 2000, 0, 0, 0);

----------------------------------------------------------------------------------------------------

INSERT INTO coordinate VALUES(1,  '서울특별시 강남구 역삼1동',  '37.4954841',  '127.0333574');
INSERT INTO coordinate VALUES(2,  '서울특별시 강남구 역삼2동',  '37.4959674',  '127.0468034');
INSERT INTO coordinate VALUES(3,  '서울특별시 강남구 논현1동',  '37.5115706 ', '127.028461');
INSERT INTO coordinate VALUES(4,  '서울특별시 강남구 논현2동',  '37.517342',   '127.037213');
INSERT INTO coordinate VALUES(5,  '서울특별시 강남구 삼성1동',  '37.5144424',  '127.062532');
INSERT INTO coordinate VALUES(6,  '서울특별시 강남구 삼성2동',  '37.5112',     '127.04595');
INSERT INTO coordinate VALUES(7,  '서울특별시 강남구 대치1동',  '37.4931821',  '127.0567047');
INSERT INTO coordinate VALUES(8,  '서울특별시 강남구 대치2동',  '37.5022848',  '127.0642072');
INSERT INTO coordinate VALUES(9,  '서울특별시 강남구 대치4동',  '37.4997415',  '127.0579127');
INSERT INTO coordinate VALUES(10, '서울특별시 강남구 도곡1동',  '37.488238',   '127.0390246');
INSERT INTO coordinate VALUES(11, '서울특별시 강남구 도곡2동',  '37.4837425',  '127.0464338');
INSERT INTO coordinate VALUES(12, '서울특별시 강남구 청담동',   '37.525107',   '127.049291');
INSERT INTO coordinate VALUES(13, '서울특별시 강남구 신사동',   '37.5240101',  '127.0227814');
INSERT INTO coordinate VALUES(14, '서울특별시 강남구 압구정동', '37.530642',   '127.030713');
INSERT INTO coordinate VALUES(15, '서울특별시 강남구 개포1동',  '37.4827409',  '127.055737');
INSERT INTO coordinate VALUES(16, '서울특별시 강남구 개포4동',  '37.4771404',  '127.0497486');
INSERT INTO coordinate VALUES(17, '서울특별시 강남구 일원1동',  '37.491839',   '127.0879629');
INSERT INTO coordinate VALUES(18, '서울특별시 강남구 일원2동',  '37.4922048',  '127.0737224');
INSERT INTO coordinate VALUES(19, '서울특별시 강남구 일원본동', '37.4833484',  '127.0864633');
INSERT INTO coordinate VALUES(20, '서울특별시 강남구 세곡동',   '37.4643683',  '127.1043555');
--------------------------------------------------
INSERT INTO coordinate VALUES(21, '서울특별시 송파구 잠실본동', '37.5060716', '127.0832037');
INSERT INTO coordinate VALUES(22, '서울특별시 송파구 잠실2동',  '37.5119488', '127.088559');
INSERT INTO coordinate VALUES(23, '서울특별시 송파구 잠실3동',  '37.513333',  '127.094375');
INSERT INTO coordinate VALUES(24, '서울특별시 송파구 잠실4동',  '37.5201089', '127.1122668');
INSERT INTO coordinate VALUES(25, '서울특별시 송파구 잠실6동',  '37.518142',  '127.10065');
INSERT INTO coordinate VALUES(26, '서울특별시 송파구 잠실7동',  '37.5086777', '127.0771146');
INSERT INTO coordinate VALUES(27, '서울특별시 송파구 풍납1동',  '37.538092',  '127.122075');
INSERT INTO coordinate VALUES(28, '서울특별시 송파구 풍납2동',  '37.528833',  '127.116825');
INSERT INTO coordinate VALUES(29, '서울특별시 송파구 삼전동',   '37.502714',  '127.0925354');
INSERT INTO coordinate VALUES(30, '서울특별시 송파구 석촌동',   '37.503592',  '127.1037');
INSERT INTO coordinate VALUES(31, '서울특별시 송파구 송파1동',  '37.5062595', '127.1093393');
INSERT INTO coordinate VALUES(32, '서울특별시 송파구 송파2동',  '37.502317',  '127.1167651');
INSERT INTO coordinate VALUES(33, '서울특별시 송파구 방이1동',  '37.510933',  '127.123925');
INSERT INTO coordinate VALUES(34, '서울특별시 송파구 방이2동',  '37.5164444', '127.1114868');
INSERT INTO coordinate VALUES(35, '서울특별시 송파구 오륜동',   '37.515425',  '127.1343');
INSERT INTO coordinate VALUES(36, '서울특별시 송파구 오금동',   '37.5030528', '127.1281494');
INSERT INTO coordinate VALUES(37, '서울특별시 송파구 가락본동', '37.4955568', '127.1217864');
INSERT INTO coordinate VALUES(38, '서울특별시 송파구 가락1동',  '37.4964898', '127.1098653');
INSERT INTO coordinate VALUES(39, '서울특별시 송파구 가락2동',  '37.4987054', '127.1266714');
INSERT INTO coordinate VALUES(40, '서울특별시 송파구 문정1동',  '37.4900982', '127.1241719');
INSERT INTO coordinate VALUES(41, '서울특별시 송파구 문정2동',  '37.489823',  '127.1108107');
INSERT INTO coordinate VALUES(42, '서울특별시 송파구 장지동',   '37.4785',    '127.1354');
INSERT INTO coordinate VALUES(43, '서울특별시 송파구 거여1동',  '37.4969643', '127.1432324');
INSERT INTO coordinate VALUES(44, '서울특별시 송파구 거여2동',  '37.4935803', '127.1468764');
INSERT INTO coordinate VALUES(45, '서울특별시 송파구 마천1동',  '37.4960195', '127.1499724');
INSERT INTO coordinate VALUES(46, '서울특별시 송파구 마천2동',  '37.4968477', '127.1485193');
INSERT INTO coordinate VALUES(47, '서울특별시 송파구 위례동',   '37.4811656', '127.1439378');
--------------------------------------------------
INSERT INTO Coordinate VALUES(48, '서울특별시 강동구 암사1동',  '37.551508',  '127.132663');
INSERT INTO Coordinate VALUES(49, '서울특별시 강동구 암사2동',  '37.5517481', '127.1272074');
INSERT INTO Coordinate VALUES(50, '서울특별시 강동구 암사3동',  '37.5549912', '127.1408249');
INSERT INTO Coordinate VALUES(51, '서울특별시 강동구 고덕1동',  '37.5572593', '127.1515382');
INSERT INTO Coordinate VALUES(52, '서울특별시 강동구 고덕2동',  '37.5605',    '127.16435');
INSERT INTO Coordinate VALUES(53, '서울특별시 강동구 강일동',   '37.5649783', '127.173909');
INSERT INTO Coordinate VALUES(54, '서울특별시 강동구 명일1동',  '37.5512453', '127.1443656');
INSERT INTO Coordinate VALUES(55, '서울특별시 강동구 명일2동',  '37.546366',  '127.1513427');
INSERT INTO Coordinate VALUES(56, '서울특별시 강동구 천호1동',  '37.5450159', '127.1368066');
INSERT INTO Coordinate VALUES(57, '서울특별시 강동구 천호2동',  '37.5435257', '127.1254351');
INSERT INTO Coordinate VALUES(58, '서울특별시 강동구 천호3동',  '37.5361455', '127.1332269');
INSERT INTO Coordinate VALUES(59, '서울특별시 강동구 길동',     '37.5345598', '127.1426791');
INSERT INTO Coordinate VALUES(60, '서울특별시 강동구 상일1동',  '37.5506614', '127.1649058');
INSERT INTO Coordinate VALUES(61, '서울특별시 강동구 상일2동',  '37.5499518', '127.1758801');
INSERT INTO Coordinate VALUES(62, '서울특별시 강동구 성내1동',  '37.5304417', '127.122425');
INSERT INTO Coordinate VALUES(63, '서울특별시 강동구 성내2동',  '37.532425',  '127.129563');
INSERT INTO Coordinate VALUES(64, '서울특별시 강동구 둔촌1동',  '37.5333656', '127.1419851');
INSERT INTO Coordinate VALUES(65, '서울특별시 강동구 둔촌2동',  '37.5332885', '127.1419221');
--------------------------------------------------
INSERT INTO Coordinate VALUES(66,'서울특별시 서초구 방배동',37.4941452,126.988785);
INSERT INTO Coordinate VALUES(67,'서울특별시 서초구 양재동',37.470101,127.039888);
INSERT INTO Coordinate VALUES(68,'서울특별시 서초구 우면동',37.466,127.0169);
INSERT INTO Coordinate VALUES(69,'서울특별시 서초구 원지동',37.4452,127.0476);
INSERT INTO Coordinate VALUES(70,'서울특별시 서초구 잠원동',37.5149619,127.0140771);
INSERT INTO Coordinate VALUES(71,'서울특별시 서초구 반포동',37.5021441,126.9868224);
INSERT INTO Coordinate VALUES(72,'서울특별시 서초구 서초동',37.4901186,127.0195082);
INSERT INTO Coordinate VALUES(73,'서울특별시 서초구 내곡동',37.4493583,127.0583103);
INSERT INTO Coordinate VALUES(74,'서울특별시 서초구 염곡동',37.462035,127.054288);
INSERT INTO Coordinate VALUES(75,'서울특별시 서초구 신원동',37.4448,127.0641);
INSERT INTO Coordinate VALUES(76,'서울특별시 서초구 서초1동',37.4901186,127.0195082);
INSERT INTO Coordinate VALUES(77,'서울특별시 서초구 서초2동',37.49208,127.02496);
INSERT INTO Coordinate VALUES(78,'서울특별시 서초구 서초3동',37.4836682,127.0119725);
INSERT INTO Coordinate VALUES(79,'서울특별시 서초구 서초4동',37.5026685,127.0221589);
INSERT INTO Coordinate VALUES(80,'서울특별시 서초구 반포본동',37.5021441,126.9868224);
INSERT INTO Coordinate VALUES(81,'서울특별시 서초구 반포1동',37.5050895,127.0135073);
INSERT INTO Coordinate VALUES(82,'서울특별시 서초구 반포2동',37.5046022,126.9945091);
INSERT INTO Coordinate VALUES(83,'서울특별시 서초구 반포3동',37.5120925,127.0056289);
INSERT INTO Coordinate VALUES(84,'서울특별시 서초구 반포4동',37.4974314,127.0003853);
INSERT INTO Coordinate VALUES(85,'서울특별시 서초구 방배본동',37.4941452,126.988785);
INSERT INTO Coordinate VALUES(86,'서울특별시 서초구 방배1동',37.4832801,126.9945569);
INSERT INTO Coordinate VALUES(87,'서울특별시 서초구 방배2동',37.4797439,126.9855106);
INSERT INTO Coordinate VALUES(88,'서울특별시 서초구 방배3동',37.4784409,126.9999918);
INSERT INTO Coordinate VALUES(89,'서울특별시 서초구 방배4동',37.489007,126.9923344);
INSERT INTO Coordinate VALUES(90,'서울특별시 서초구 양재1동',37.4716412,127.026744);
INSERT INTO Coordinate VALUES(91,'서울특별시 서초구 양재2동',37.470601,127.041188);
--------------------------------------------------
INSERT INTO Coordinate VALUES(92,'서울특별시 동작구 노량진동',37.512308,126.942013);
INSERT INTO Coordinate VALUES(93,'서울특별시 동작구 상도동',37.4981,126.953089);
INSERT INTO Coordinate VALUES(94,'서울특별시 동작구 상도1동',37.4981,126.953089);
INSERT INTO Coordinate VALUES(95,'서울특별시 동작구 본동',37.5124285,126.9536286);
INSERT INTO Coordinate VALUES(96,'서울특별시 동작구 흑석동',37.5058617,126.966612);
INSERT INTO Coordinate VALUES(97,'서울특별시 동작구 동작동',37.4991998,126.9738471);
INSERT INTO Coordinate VALUES(98,'서울특별시 동작구 사당동',37.4830639,126.9786682);
INSERT INTO Coordinate VALUES(99,'서울특별시 동작구 대방동',37.508133,126.92635);
INSERT INTO Coordinate VALUES(100,'서울특별시 동작구 신대방동',37.488943,126.91005);
INSERT INTO Coordinate VALUES(101,'서울특별시 동작구 노량진1동',37.512308,126.942013);
INSERT INTO Coordinate VALUES(102,'서울특별시 동작구 노량진2동',37.508509,126.937352);
INSERT INTO Coordinate VALUES(103,'서울특별시 동작구 상도2동',37.5055197,126.942296);
INSERT INTO Coordinate VALUES(104,'서울특별시 동작구 상도3동',37.4991123,126.9313793);
INSERT INTO Coordinate VALUES(105,'서울특별시 동작구 상도4동',37.4994203,126.941422);
INSERT INTO Coordinate VALUES(106,'서울특별시 동작구 사당1동',37.4830639,126.9786682);
INSERT INTO Coordinate VALUES(107,'서울특별시 동작구 사당2동',37.4887323,126.9792598);
INSERT INTO Coordinate VALUES(108,'서울특별시 동작구 사당3동',37.4845247,126.9734908);
INSERT INTO Coordinate VALUES(109,'서울특별시 동작구 사당4동',37.4809888,126.9716704);
INSERT INTO Coordinate VALUES(110,'서울특별시 동작구 사당5동',37.4857521,126.9668658);
INSERT INTO Coordinate VALUES(111,'서울특별시 동작구 신대방1동',37.488943,126.91005);
INSERT INTO Coordinate VALUES(112,'서울특별시 동작구 신대방2동',37.4984422,126.9244085);
--------------------------------------------------
INSERT INTO Coordinate VALUES(113,'서울특별시 관악구 봉천동',37.4779619,126.9534602);
INSERT INTO Coordinate VALUES(114,'서울특별시 관악구 신림동',37.487426,126.927075);
INSERT INTO Coordinate VALUES(115,'서울특별시 관악구 남현동',37.4745394,126.9778366);
INSERT INTO Coordinate VALUES(116,'서울특별시 관악구 보라매동',37.4881456,126.9327389);
INSERT INTO Coordinate VALUES(117,'서울특별시 관악구 청림동',37.4918329,126.9585773);
INSERT INTO Coordinate VALUES(118,'서울특별시 관악구 성현동',37.4895366,126.9481271);
INSERT INTO Coordinate VALUES(119,'서울특별시 관악구 행운동',37.4806541,126.9570456);
INSERT INTO Coordinate VALUES(120,'서울특별시 관악구 낙성대동',37.4762971,126.9583884);
INSERT INTO Coordinate VALUES(121,'서울특별시 관악구 청룡동',37.4791304,126.9416518);
INSERT INTO Coordinate VALUES(122,'서울특별시 관악구 은천동',37.4853086,126.9424278);
INSERT INTO Coordinate VALUES(123,'서울특별시 관악구 중앙동',37.4842598,126.9497133);
INSERT INTO Coordinate VALUES(124,'서울특별시 관악구 인헌동',37.4750974,126.9652628);
INSERT INTO Coordinate VALUES(125,'서울특별시 관악구 서원동',37.4797346,126.9313);
INSERT INTO Coordinate VALUES(126,'서울특별시 관악구 신원동',37.4815883,126.9273519);
INSERT INTO Coordinate VALUES(127,'서울특별시 관악구 서림동',37.4749956,126.9349995);
INSERT INTO Coordinate VALUES(128,'서울특별시 관악구 신사동',37.5240101,127.0227814);
INSERT INTO Coordinate VALUES(129,'서울특별시 관악구 난향동',37.461429,126.918842);
INSERT INTO Coordinate VALUES(130,'서울특별시 관악구 조원동',37.4826299,126.9078649);
INSERT INTO Coordinate VALUES(131,'서울특별시 관악구 대학동',37.4706145,126.9369907);
INSERT INTO Coordinate VALUES(132,'서울특별시 관악구 삼성동',37.470101,126.932963);
INSERT INTO Coordinate VALUES(133,'서울특별시 관악구 미성동',37.4761761,126.9155534);
INSERT INTO Coordinate VALUES(134,'서울특별시 관악구 난곡동',37.4709634,126.9216507);
--------------------------------------------------
INSERT INTO Coordinate VALUES(135,'서울특별시 광진구 중곡동',37.560675,127.080038);
INSERT INTO Coordinate VALUES(136,'서울특별시 광진구 능동',37.5537803,127.0804994);
INSERT INTO Coordinate VALUES(137,'서울특별시 광진구 구의동',37.5424917,127.0856756);
INSERT INTO Coordinate VALUES(138,'서울특별시 광진구 광장동',37.546892,127.103025);
INSERT INTO Coordinate VALUES(139,'서울특별시 광진구 자양동',37.534508,127.082438);
INSERT INTO Coordinate VALUES(140,'서울특별시 광진구 화양동',37.5465421,127.0713152);
INSERT INTO Coordinate VALUES(141,'서울특별시 광진구 군자동',37.5554853,127.0753494);
INSERT INTO Coordinate VALUES(142,'서울특별시 광진구 중곡1동',37.560675,127.080038);
INSERT INTO Coordinate VALUES(143,'서울특별시 광진구 중곡2동',37.560308,127.081487);
INSERT INTO Coordinate VALUES(144,'서울특별시 광진구 중곡3동',37.5688084,127.0801736);
INSERT INTO Coordinate VALUES(145,'서울특별시 광진구 중곡4동',37.5590865,127.0894538);
INSERT INTO Coordinate VALUES(146,'서울특별시 광진구 자양1동',37.534508,127.082438);
INSERT INTO Coordinate VALUES(147,'서울특별시 광진구 자양2동',37.528825,127.084438);
INSERT INTO Coordinate VALUES(148,'서울특별시 광진구 자양3동',37.533842,127.072913);
INSERT INTO Coordinate VALUES(149,'서울특별시 광진구 자양4동',37.5341115,127.0662969);
INSERT INTO Coordinate VALUES(150,'서울특별시 광진구 구의1동 ',37.5424917,127.0856756);
INSERT INTO Coordinate VALUES(151,'서울특별시 광진구 구의2동',37.5472481,127.0899111);
INSERT INTO Coordinate VALUES(152,'서울특별시 광진구 구의3동',37.5380706,127.0920204);
--------------------------------------------------
INSERT INTO Coordinate VALUES(153,'서울특별시 성동구 상왕십리동',37.568375 ,127.0245375);
INSERT INTO Coordinate VALUES(154,'서울특별시 성동구 하왕십리동',37.5641583 ,127.02875);
INSERT INTO Coordinate VALUES(155,'서울특별시 성동구 홍익동',37.56695,127.031975);
INSERT INTO Coordinate VALUES(156,'서울특별시 성동구 도선동',37.563542,127.033725);
INSERT INTO Coordinate VALUES(157,'서울특별시 성동구 마장동',37.566325,127.045388);
INSERT INTO Coordinate VALUES(158,'서울특별시 성동구 사근동',37.5614846,127.0453315);
INSERT INTO Coordinate VALUES(159,'서울특별시 성동구 행당동',37.558567,127.036188);
INSERT INTO Coordinate VALUES(160,'서울특별시 성동구 응봉동',37.5531636,127.0334357);
INSERT INTO Coordinate VALUES(161,'서울특별시 성동구 금호동1가',37.553,127.0263);
INSERT INTO Coordinate VALUES(162,'서울특별시 성동구 금호동2가',37.553808,127.01885);
INSERT INTO Coordinate VALUES(163,'서울특별시 성동구 금호동3가',37.5498,127.0193);
INSERT INTO Coordinate VALUES(164,'서울특별시 성동구 금호동4가',37.5453855,127.0241155);
INSERT INTO Coordinate VALUES(165,'서울특별시 성동구 옥수동',37.5436386,127.0134622);
INSERT INTO Coordinate VALUES(166,'서울특별시 성동구 성수동1가',37.542108,127.04965);
INSERT INTO Coordinate VALUES(167,'서울특별시 성동구 성수동2가',37.539817,127.056888);
INSERT INTO Coordinate VALUES(168,'서울특별시 성동구 송정동',37.5545217,127.0696004);
INSERT INTO Coordinate VALUES(169,'서울특별시 성동구 용답동',37.5640618,127.0555515);
INSERT INTO Coordinate VALUES(170,'서울특별시 성동구 왕십리2동',37.5617987,127.0310207);
INSERT INTO Coordinate VALUES(171,'서울특별시 성동구 왕십리도선동',37.5678257,127.0255501);
INSERT INTO Coordinate VALUES(172,'서울특별시 성동구 행당1동',37.558567,127.036188);
INSERT INTO Coordinate VALUES(173,'서울특별시 성동구 행당2동',37.558208,127.02935);
INSERT INTO Coordinate VALUES(174,'서울특별시 성동구 금호1가동',37.5549008,127.0216354);
INSERT INTO Coordinate VALUES(175,'서울특별시 성동구 금호2.3가동',37.5532922,127.0209487);
INSERT INTO Coordinate VALUES(176,'서울특별시 성동구 금호4가동',37.5471987,127.02241);
INSERT INTO Coordinate VALUES(177,'서울특별시 성동구 성수1가1동',37.542108,127.04965);
INSERT INTO Coordinate VALUES(178,'서울특별시 성동구 성수1가2동',37.5464774,127.0443034);
INSERT INTO Coordinate VALUES(179,'서울특별시 성동구 성수2가1동',37.5395907,127.0540659);
INSERT INTO Coordinate VALUES(180,'서울특별시 성동구 성수2가3동',37.5482223,127.0552645);
--------------------------------------------------
INSERT INTO Coordinate VALUES(181,'서울특별시 용산구 후암동',37.5486375,126.9781344);
INSERT INTO Coordinate VALUES(182,'서울특별시 용산구 용산동2가',37.54295,126.98405);
INSERT INTO Coordinate VALUES(183,'서울특별시 용산구 용산동4가',37.5306,126.9854);
INSERT INTO Coordinate VALUES(184,'서울특별시 용산구 갈월동',37.5425333,126.9717625);
INSERT INTO Coordinate VALUES(185,'서울특별시 용산구 남영동',37.5457586,126.9748403);
INSERT INTO Coordinate VALUES(186,'서울특별시 용산구 용산동1가',37.5401,126.977);
INSERT INTO Coordinate VALUES(187,'서울특별시 용산구 동자동',37.5520533,126.9723999);
INSERT INTO Coordinate VALUES(188,'서울특별시 용산구 서계동',37.5524583,126.9664);
INSERT INTO Coordinate VALUES(189,'서울특별시 용산구 청파동1가',37.5488,126.9674);
INSERT INTO Coordinate VALUES(190,'서울특별시 용산구 청파동2가',37.5462,126.9667);
INSERT INTO Coordinate VALUES(191,'서울특별시 용산구 청파동3가',37.5432,126.9668);
INSERT INTO Coordinate VALUES(192,'서울특별시 용산구 원효로1가',37.5398,126.9673625);
INSERT INTO Coordinate VALUES(193,'서울특별시 용산구 원효로2가',37.536775,126.963225);
INSERT INTO Coordinate VALUES(194,'서울특별시 용산구 신창동',37.5358417,126.9547);
INSERT INTO Coordinate VALUES(195,'서울특별시 용산구 산천동',37.5353,126.9513);
INSERT INTO Coordinate VALUES(196,'서울특별시 용산구 청암동',37.5344784,126.9466319);
INSERT INTO Coordinate VALUES(197,'서울특별시 용산구 원효로3가',37.5344,126.9581);
INSERT INTO Coordinate VALUES(198,'서울특별시 용산구 원효로4가',37.5328,126.9504);
INSERT INTO Coordinate VALUES(199,'서울특별시 용산구 효창동',37.5424569,126.9618498);
INSERT INTO Coordinate VALUES(200,'서울특별시 용산구 도원동',37.5388,126.9561);
INSERT INTO Coordinate VALUES(201,'서울특별시 용산구 용문동',37.5389,126.957562);
INSERT INTO Coordinate VALUES(202,'서울특별시 용산구 문배동',37.537,126.969088);
INSERT INTO Coordinate VALUES(203,'서울특별시 용산구 신계동',37.5358,126.9667);
INSERT INTO Coordinate VALUES(204,'서울특별시 용산구 한강로1가',37.5362,126.973488);
INSERT INTO Coordinate VALUES(205,'서울특별시 용산구 한강로2가',37.5316667,126.968725);
INSERT INTO Coordinate VALUES(206,'서울특별시 용산구 용산동3가',37.532,126.9768);
INSERT INTO Coordinate VALUES(207,'서울특별시 용산구 용산동5가',37.5257,126.9747);
INSERT INTO Coordinate VALUES(208,'서울특별시 용산구 한강로3가',37.5278,126.9602);
INSERT INTO Coordinate VALUES(209,'서울특별시 용산구 이촌동',37.5214167,126.9732);
INSERT INTO Coordinate VALUES(210,'서울특별시 용산구 이태원동',37.5325225,126.9950384);
INSERT INTO Coordinate VALUES(211,'서울특별시 용산구 동빙고동',37.5237,126.9957);
INSERT INTO Coordinate VALUES(212,'서울특별시 용산구 서빙고동',37.520458,126.994663);
INSERT INTO Coordinate VALUES(213,'서울특별시 용산구 주성동',37.5214781,126.9995663);
INSERT INTO Coordinate VALUES(214,'서울특별시 용산구 용산동6가',37.5169,126.984);
INSERT INTO Coordinate VALUES(215,'서울특별시 용산구 보광동',37.5263257,127.0001818);
INSERT INTO Coordinate VALUES(216,'서울특별시 용산구 용산2가동',37.5461327,126.9855777);
INSERT INTO Coordinate VALUES(217,'서울특별시 용산구 청파동1가',37.5488,126.9674);
INSERT INTO Coordinate VALUES(218,'서울특별시 용산구 원효로1동',37.5372185,126.9676811);
INSERT INTO Coordinate VALUES(219,'서울특별시 용산구 원효로2동',37.53435,126.951538);
INSERT INTO Coordinate VALUES(220,'서울특별시 용산구 한강로동',37.5281071,126.9692332);
INSERT INTO Coordinate VALUES(221,'서울특별시 용산구 이촌1동',37.5214167,126.9732);
INSERT INTO Coordinate VALUES(222,'서울특별시 용산구 이촌2동',37.5262575,126.9547071);
INSERT INTO Coordinate VALUES(223,'서울특별시 용산구 이태원1동',37.5325225,126.9950384);
INSERT INTO Coordinate VALUES(224,'서울특별시 용산구 이태원2동',37.5418962,126.9901481);

--------------------------------------------------------------------------------------------

INSERT INTO town VALUES(1, 0, 1);

----------------------------------------------------------------------------------------------------

INSERT INTO category VALUES(1,'디지털/가전','');

INSERT INTO category VALUES(2,'모바일',1);
INSERT INTO category VALUES(3,'가전제품',1);
INSERT INTO category VALUES(4,'오디오/영상/관련기기',1);
INSERT INTO category VALUES(5,'PC/노트북',1);
INSERT INTO category VALUES(6,'게임/타이틀',1);
INSERT INTO category VALUES(7,'카메라/DSLR',1);
INSERT INTO category VALUES(8,'PC부품/저장장치',1);
--------------------------------------------------
INSERT INTO category VALUES(9,'가구/인테리어','');

INSERT INTO category VALUES(10,'가구',9);
INSERT INTO category VALUES(11,'인테리어',9);
--------------------------------------------------
INSERT INTO category VALUES(12,'생활/가공식품','');

INSERT INTO category VALUES(13,'주방용품',12);
INSERT INTO category VALUES(14,'생활용품',12);
INSERT INTO category VALUES(15,'식품',12);
INSERT INTO category VALUES(16,'산업용품',12);
--------------------------------------------------
INSERT INTO category VALUES(17,'유아동','');

INSERT INTO category VALUES(18,'베이비의류(0~2세)',17);
INSERT INTO category VALUES(19,'여아의류(3~6세)',17);
INSERT INTO category VALUES(20,'여주니어의류(7세~)',17);
INSERT INTO category VALUES(21,'남아의류(3~6세)',17);
INSERT INTO category VALUES(22,'남주니어의류(7세~)',17);
INSERT INTO category VALUES(23,'유아동신발/잡화',17);
INSERT INTO category VALUES(24,'교육/완구/인형',17);
INSERT INTO category VALUES(25,'유아동용품',17);
INSERT INTO category VALUES(26,'이유용품/유아식기',17);
--------------------------------------------------
INSERT INTO category VALUES(27,'남성의류','');

INSERT INTO category VALUES(28,'패딩/점퍼',27);
INSERT INTO category VALUES(29,'코트',27);
INSERT INTO category VALUES(30,'맨투맨',27);
INSERT INTO category VALUES(31,'후드티/후드집업',27);
INSERT INTO category VALUES(32,'티셔츠',27);
INSERT INTO category VALUES(33,'셔츠',27);
INSERT INTO category VALUES(34,'가디건',27);
INSERT INTO category VALUES(35,'니트/스웨터',27);
INSERT INTO category VALUES(36,'바지',27);
INSERT INTO category VALUES(37,'청바지',27);
INSERT INTO category VALUES(38,'반바지',27);
INSERT INTO category VALUES(39,'자켓',27);
INSERT INTO category VALUES(40,'정장',27);
INSERT INTO category VALUES(41,'조끼/트레이닝',27);
--------------------------------------------------
INSERT INTO category VALUES(42,'여성의류','');

INSERT INTO category VALUES(43,'패딩/점퍼',42);
INSERT INTO category VALUES(44,'코트',42);
INSERT INTO category VALUES(45,'맨투맨',42);
INSERT INTO category VALUES(46,'후드티/후드집업',42);
INSERT INTO category VALUES(47,'티셔츠',42);
INSERT INTO category VALUES(48,'셔츠',42);
INSERT INTO category VALUES(49,'가디건',42);
INSERT INTO category VALUES(50,'니트/스웨터',42);
INSERT INTO category VALUES(51,'바지',42);
INSERT INTO category VALUES(52,'청바지',42);
INSERT INTO category VALUES(53,'반바지',42);
INSERT INTO category VALUES(54,'자켓',42);
INSERT INTO category VALUES(55,'정장',42);
INSERT INTO category VALUES(56,'조끼/트레이닝',42);
--------------------------------------------------
INSERT INTO category VALUES(57,'가방/잡화','');

INSERT INTO category VALUES(58,'남성가방',57);
INSERT INTO category VALUES(59,'여성가방',57);
INSERT INTO category VALUES(60,'여행용',57);
--------------------------------------------------
INSERT INTO category VALUES(61,'뷰티/미용','');

INSERT INTO category VALUES(62,'스킨케어',61);
INSERT INTO category VALUES(63,'색조메이크업',61);
INSERT INTO category VALUES(64,'바디/헤어케어',61);
INSERT INTO category VALUES(65,'향수/아로마',61);
INSERT INTO category VALUES(66,'네일아트/케어',61);
INSERT INTO category VALUES(67,'미용소품/기기',61);
INSERT INTO category VALUES(68,'다이어트/이너뷰티',61);
INSERT INTO category VALUES(69,'남성 화장품',61);
INSERT INTO category VALUES(70,'여성 화장품',61);
--------------------------------------------------
INSERT INTO category VALUES(71,'스포츠/레저','');

INSERT INTO category VALUES(72,'골프',71);
INSERT INTO category VALUES(73,'캠핑',71);
INSERT INTO category VALUES(74,'낚시',71);
INSERT INTO category VALUES(75,'축구',71);
INSERT INTO category VALUES(76,'야구',71);
INSERT INTO category VALUES(77,'농구',71);
INSERT INTO category VALUES(78,'탁구',71);
INSERT INTO category VALUES(79,'당구',71);
INSERT INTO category VALUES(80,'볼링',71);
INSERT INTO category VALUES(81,'테니스',71);
INSERT INTO category VALUES(82,'자전거',71);
INSERT INTO category VALUES(83,'등산/클라이밍',71);
INSERT INTO category VALUES(84,'헬스/요가/필라테스',71);
INSERT INTO category VALUES(85,'배드민턴',71);
--------------------------------------------------
INSERT INTO category VALUES(86,'게임/음반','');

INSERT INTO category VALUES(87,'CD/DVD/LP',86);
INSERT INTO category VALUES(88,'악기',86);
INSERT INTO category VALUES(89,'게임CD',86);
--------------------------------------------------
INSERT INTO category VALUES(90,'도서/티켓','');

INSERT INTO category VALUES(91,'도서',90);
INSERT INTO category VALUES(92,'문구',90);
INSERT INTO category VALUES(93,'기프티콘/쿠폰',90);
INSERT INTO category VALUES(94,'상품권',90);
INSERT INTO category VALUES(95,'티켓',90);
--------------------------------------------------
INSERT INTO category VALUES(96,'반려동물용품','');

INSERT INTO category VALUES(97,'강아지 용품',96);
INSERT INTO category VALUES(98,'강아지 사료/간식',96);
INSERT INTO category VALUES(99,'기타(강아지)',96);
INSERT INTO category VALUES(100,'고양이 용품',96);
INSERT INTO category VALUES(101,'고양이 사료/간식',96);
INSERT INTO category VALUES(102,'기타(고양이)',96);
INSERT INTO category VALUES(103,'기타(반려동물 용품)',96);
INSERT INTO category VALUES(104,'기타(반려동물 사료/간식)',96);
--------------------------------------------------
INSERT INTO category VALUES(105,'식물','');

INSERT INTO category VALUES(106,'꽃',105);
INSERT INTO category VALUES(107,'다육이/선인장',105);
INSERT INTO category VALUES(108,'관상수/나무',105);
INSERT INTO category VALUES(109,'허브',105);
INSERT INTO category VALUES(110,'난초',105);
INSERT INTO category VALUES(111,'채소/과일',105);
INSERT INTO category VALUES(112,'수경식물',105);
INSERT INTO category VALUES(113,'에어플랜트',105);
--------------------------------------------------
INSERT INTO category VALUES(114,'기타','');
INSERT INTO category VALUES(115,'기타상품',114);

----------------------------------------------------------------------------------------------------
INSERT INTO KEYWORD VALUES(1,2,'디지털');
INSERT INTO KEYWORD VALUES(2,2,'가구');
INSERT INTO KEYWORD VALUES(3,2,'생활');
INSERT INTO KEYWORD VALUES(4,2,'유아');
INSERT INTO KEYWORD VALUES(5,2,'남성');
INSERT INTO KEYWORD VALUES(6,3,'여성');
INSERT INTO KEYWORD VALUES(7,3,'의류');
INSERT INTO KEYWORD VALUES(8,3,'뷰티');
INSERT INTO KEYWORD VALUES(9,3,'미용');
INSERT INTO KEYWORD VALUES(10,3,'스포츠');
INSERT INTO KEYWORD VALUES(11,4,'게임');
INSERT INTO KEYWORD VALUES(12,4,'음반');
INSERT INTO KEYWORD VALUES(13,4,'도서');
INSERT INTO KEYWORD VALUES(14,4,'티켓');
INSERT INTO KEYWORD VALUES(15,4,'반려동물');
INSERT INTO KEYWORD VALUES(16,5,'강아지');
INSERT INTO KEYWORD VALUES(17,5,'고양이');
INSERT INTO KEYWORD VALUES(18,5,'중고');
INSERT INTO KEYWORD VALUES(19,5,'유니폼');
INSERT INTO KEYWORD VALUES(20,5,'신발');
----------------------------------------------------------------------------------------------------

INSERT INTO prohibition_keyword VALUES(1,'담배');
INSERT INTO prohibition_keyword VALUES(2,'술');
INSERT INTO prohibition_keyword VALUES(3,'소주');
INSERT INTO prohibition_keyword VALUES(4,'맥주');
INSERT INTO prohibition_keyword VALUES(5,'마약');
INSERT INTO prohibition_keyword VALUES(6,'성인');
INSERT INTO prohibition_keyword VALUES(7,'음란물');

----------------------------------------------------------------------------------------------------

INSERT INTO product VALUES (0, 0, 1, 114, '양파마켓 키워드 알림', '키워드 알림', 0, 'onion.png', SYSDATE, NULL, NULL, 0, 'SOLDOUT', 0, 1);
INSERT INTO product VALUES(1, 3, 1, 115, 'ㄷrㅁㅂrlㅍrㅁㄴiㄷr','남은거 3개비 팜', 1000, '115금지품목1.png', SYSDATE , '', '' ,0,'SALESON', 1, 0);
--------------------------------------------------
INSERT INTO product VALUES(101,1,1,43,'코오롱스포츠 구스다운 여성 롱패딩 블랙','하자없이 상태좋은편입니다 드라이 한번 하고 입으시면 될 것 같아요 사이즈 95 택포',65000,'42여성패딩1.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(102,1,1,44,'트렌치코트','소매부분은 끈으로 리본 묶을 수 있어요 뒷부분은 케이프 형태입니다.',50000,'42여성코트1.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(103,1,1,44,'55 66 톰보이 후드 코트','상태 깨끗해요 위례포레샤인 17단지에서 거래가능',20000,'42여성코트2.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(104,1,1,48,'마쥬 스트라이프 셔츠1','마쥬 스트라이프셔츠1(44-55) 새상품 반품 약46',80000,'42여성셔츠1.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(105,1,1,48,'새상품 미쏘 스트링 포인트 셔츠 원피스 네이비','빠르게 팔려고 가격 내렸습니다~',40000,'42여성셔츠2.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(106,1,1,54,'Jack Wolfskin 기능성 플리스 자켓 66~77','구입하고 실착용 5회 미만, 깨끗하게 보관했어요',50000,'42여성자켓1.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(107,1,1,54,'미착용 택포함 자라 크롭 자켓','자라 새상품 2021년 구매했는데 택 포함 미착용이에용',46000,'42여성자켓2.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(108,1,1,51,'발랭키 바지. 새옷.','사이즈 26인치. 길이 90센티.',18000,'42여성바지1.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(109,1,1,52,'스트레이트핏 세미와이드 청바지M','스트레이트 핏 세미와이드 청바지 연청~중청 사이의 예쁜 색감이에요.',5000,'42여성청바지1.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(110,1,1,97,'강아지 집 옥희독희 꿀몬스방석','구매는 바로 얼마 전에 했습니다, 사이즈 안맞아서 싸게 팝니다',43000,'96강아지용품1.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(111,1,1,97,'이동장이랑 방석 2개같이요','구매는 강아지 유치원에서 직접 선생님 추천으로 샀는데 사용 안해서 팔아요 ㅜㅡㅜ',23000,'96강아지용품2.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(112,1,1,97,'강아지 모자,양말','3,3kg 시츄가 사용하려고 샀던 모자와 양말인데 강아지가 안써서 방치해둬써용..',5000,'96강아지용품3.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(113,1,1,97,'강아지 울타리','강아지 용품입니다',15000,'96강아지용품4.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(114,1,1,98,'강아지 영양간식','안녕하세요~ 소,연어로 만든 간식입니다',8500,'96강아지간식1.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(115,1,1,100,'우다다캣 매트','매트만 판매해요~ 거의 새거인데 저렴히 가져가실 분 가져가세요',20000,'96고양이용품1.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(116,1,1,100,'미야옹철 강추템 고양이 미로터널','미야옹철 수의사쌤이 추천해줘서 구매한 고양이 미로터널인데 고양이가 안놀아줘요....',26000,'96고양이용품2.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(117,1,1,100,'리스펫 사냥본능 고양이 장난감','고양님이 관심이 1도 없어서 팔아요 건전지 교체해야합니다',9000,'96고양이용품3.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(118,1,1,101,'고양이 간식 츄르','우리집 고양이 츄르를 좋아해~ 유통기한 길어요',5000,'96고양이간식1.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(119,1,1,103,'앵무새 놀이터','사용은 많이 안했는데 나무라 배변이 잘안지워졌습니다...',33000,'96기타용품1.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(120,1,1,103,'펫테일 프론트백(블랙)','새상품/s',10000,'96기타용품2.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(121,1,1,106,'안스리움클라리네비움','안스리움클라리네비움 사진보시고 연락주세요',70000,'105꽃1.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(122,1,1,106,'몬스테라 화분','집에서 키운 몬스테라 팝니다. 잘크고 있는데 너무 커서 팔아요',15000,'105꽃2.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(123,1,1,106,'식물(삭소름 보라)','하늘하늘 보라꽃이 예쁜 순동이 삭소름..',5000,'105꽃3.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(124,1,1,106,'선착순 빨리 가져가실 돈나무 금전수입니다','높이 50 안되고 금전수 돈나무 정리해요',15000,'105꽃4.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(125,1,1,106,'건강한 황금죽 식물입니다.(시멘트 화분포함)','건강한 황금죽이고 공기정화식물로 화학물질 잘정화해요',41000,'105꽃5.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(126,1,1,107,'식물/화분,화초팔아요','새 화분에 분갈이 했어요~ 자리비움으로 정리합니다',6000,'105다육1.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(127,1,1,107,'다육이 세트','다육이 기를 줄을 몰라서 판매해요 이름도 잘 모릅니다..',10000,'105다육2.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(128,1,1,107,'식물/염자 다육식물 화초 새토분과 새도자기','미니종 염자입니다. 일교차가 커지면 잎끝이 빨갛게 물들어 더욱 이뻐요',2000,'105다육3.png',SYSDATE , '', '' ,0,'SALESON','1',0);
INSERT INTO product VALUES(129,1,1,107,'다육이','고급진 화분에 붉게 물든 다육 넘이쁘죠',13000,'105다육4.png',SYSDATE , '', '' ,0,'SALESON','',0);
INSERT INTO product VALUES(130,1,1,107,'다육 모듬입니다~','사이즈 확인하시고 상대원3동 주민센터 앞으로 오시면 되요',7000,'105다육5.png',SYSDATE , '', '' ,0,'SALESON','1',0);
----------------------------------------------------------------------------------------------------

INSERT INTO SEARCH VALUES(1,'패딩',100);
INSERT INTO SEARCH VALUES(2,'강아지',90);
INSERT INTO SEARCH VALUES(3,'노트북',80);
INSERT INTO SEARCH VALUES(4,'냉장고',70);
INSERT INTO SEARCH VALUES(5,'코트',60);
INSERT INTO SEARCH VALUES(6,'아이폰',55);
INSERT INTO SEARCH VALUES(7,'포켓몬',50);
INSERT INTO SEARCH VALUES(8,'애플',45);
INSERT INTO SEARCH VALUES(9,'선반',40);
INSERT INTO SEARCH VALUES(10,'에르메스',35);
INSERT INTO SEARCH VALUES(11,'버버리',30);
INSERT INTO SEARCH VALUES(12,'샤넬',25);
INSERT INTO SEARCH VALUES(13,'컴퓨터',20);
INSERT INTO SEARCH VALUES(14,'고양이',15);
INSERT INTO SEARCH VALUES(15,'자전거',10);

----------------------------------------------------------------------------------------------------

INSERT INTO notice VALUES (100, 1, 'QNA', '아이디/비밀번호 찾는 방법', '아이디가 기억나지 않는다면, 웹 페이지에서 아이디/비밀번호 찾기에서 등록한 이메일을 통해 확인할 수 있습니다.', '2022-11-01', default);
INSERT INTO notice VALUES (101, 1, 'QNA', '커뮤니티 가이드라인', '[ 양파마켓은 동네 이웃 간의 연결을 도와 따뜻하고 활발한 교류가 있는 지역 사회를 만들기 위해 노력하고 있습니다. ]<br/><br/>양파마켓을 사용하는 이웃 모두가 커뮤니티 가이드라인을 지켜주세요<br/><br/> * 이런 행동은 할 수 없어요. *<br/> · 판매 금지 물품 거래<br/> · 중고거래 사기 등 손해를 입히는 행위<br/> · 허위 정보 게시 등 속이거나 기만하는 행위 ' , '2022-11-01', default);
INSERT INTO notice VALUES (102, 1, 'QNA', '회원가입 방법', '* 회원가입 시 아래 내용을 참고하세요.<br/><br/>[ 1. 아이디 입력 방법 ]<br/>- 5~20자의 영문 소문자, 숫자와 특수기호 -, _ 만 사용 가능합니다.<br/><br/>[ 2. 비밀번호 입력 방법 ]<br/>- 사용 가능한 특수문자 33자: ! " # $ % & '' ( ) * + , - . / : ; ? @ [ ＼ ] ^ _ ` { | } ~ \', '2022-11-01', default);
INSERT INTO notice VALUES (103, 1, 'QNA', '도용 발생 시 대처 방법', '개인정보가 유출될 경우 다양한 피해가 발생할 수 있습니다.<br/>개인정보 도용을 막기 위해서는 안전한 계정 설정이 무엇보다 중요합니다.<br/><br/><br/>[1. 전체 로그아웃하기]<br/><br/>도용이 의심되면 가장 먼저 도용자의 로그인을 모두 로그아웃해야 합니다.<br/>※ 도용자가 비밀번호를 변경하여 로그인이 어렵다면, 비밀번호 찾기를 먼저 시도하세요.<br/><br/><br/>[2. 바뀐 계정정보 확인]<br/><br/>내정보에서 이름, 휴대전화 번호, 이메일을 확인하고 바뀐 내용이 있다면 수정하세요.<br/>계정 탈취 시 본인확인 이메일 계정의 비밀번호도 노출됐을 수 있습니다.<br/>본인확인 이메일 계정의 비밀번호도 함께 변경하는 것이 안전합니다.', '2022-11-01', default);
INSERT INTO notice VALUES (104, 1, 'QNA', '게시중단 대상 게시물 유형', '개인정보가 유출될 경우 다양한 피해가 발생할 수 있습니다. 개인정보 도용을 막기 위해서는 안전한 계정 설정이 무엇보다 중요합니다.<br/><br/><br/>[1. 전체 로그아웃하기]<br/><br/>도용이 의심되면 가장 먼저 도용자의 로그인을 모두 로그아웃해야 합니다.<br/>※ 도용자가 비밀번호를 변경하여 로그인이 어렵다면, 비밀번호 찾기를 먼저 시도하세요.<br/><br/>[2. 바뀐 계정정보 확인]<br/><br/>내정보에서 이름, 휴대전화 번호, 이메일을 확인하고 바뀐 내용이 있다면 수정하세요.<br/>계정 탈취 시 본인확인 이메일 계정의 비밀번호도 노출됐을 수 있습니다.<br/>본인확인 이메일 계정의 비밀번호도 함께 변경하는 것이 안전합니다.', '2022-11-01', default);
INSERT INTO notice VALUES (105, 1, 'QNA', '유해 게시물 정의 - 불법성 게시물', '※ 불법성 게시물은 현행법에 위배되거나 범죄 및 불법 행위에 악용될 수 있는 정보를 담은 게시물입니다. ※<br/><br/><br/>[ 양파마켓에서 제한하는 음란성 게시물의 대표적인 대상은 아래와 같습니다. ]<br/><br/>- 성 관련 범죄 정보 (타인의 신체를 본인의 의사에 반해 촬영 및 게시하여 성적 수치심을 유발하는 경우, 원조교제/성매매 관련 정보 등)<br/>- 생명 관련 범죄 정보 (자살 조장, 임신중절 알선/소개, 난자/대리모/장기/신생아 매매 및 알선, 살인청부 관련 내용 등)<br/>- 개인정보 관련 범죄 정보 (개인정보의 매매 시도, 개인 계좌 대여, 개인정보 위조 변경 관련 내용 등)' , '2022-11-02', default);
INSERT INTO notice VALUES (106, 1, 'QNA', '유해 게시물 정의 - 청소년 유해 게시물', '※ 청소년유해 게시물은 어린이와 청소년의 정신적, 신체적 건강을 해칠 우려가 있는 선정적인 내용을 담은 게시물입니다. ※<br/><br/><br/>청소년보호법 제10조에 의해 19세 이상의 성인만 이용할 수 있도록 별도의 청소년유해표시를 하도록 법으로 정해져있습니다.<br/><br/>[ 양파마켓에서 제한하는 음란성 게시물의 대표적인 대상은 아래와 같습니다. ]<br/><br/>- 전신전라 노출, 둔부, 유두·유륜(여성)의 노출을 포함한 게시글<br/>- 성행위 및 자위행위에 대한 노골적인 묘사 및 내용이 포함된 게시글<br/>- 성행위 파트너, 성인물을 구하는 내용이 포함된 게시글<br/>- 청소년유해매체로 고시된 물건을 판매하거나, 거래를 알선하는 게시글 (담배, 주류, 성인 용품, 별지시기, 아이템 거래, 게임 ID 계정 거래 등)<br/>- 혐오스럽거나 불쾌감을 유발하여(신체가 훼손, 혈흔 낭자) 사회 통념상 청소년에게 유해하다고 인정되는 게시글<br/>- 기타 청소년의 건전한 생활태도 및 정신건강을 저해시킬 수 있는 게시글 ' , '2022-11-02', default);
INSERT INTO notice VALUES (107, 1, 'QNA', '유해 게시물 정의 - 음란성 게시물', '※ 음란성 게시물은 성적 수치심을 유발하여 선량한 성적 관점에 반하는 내용을 담은 게시물입니다. ※<br/><br/>또한, 현행법상 정보통신망을 통한 유통이 금지된 불법정보입니다.<br/><br/><br/>[ 양파마켓에서 제한하는 음란성 게시물의 대표적인 대상은 아래와 같습니다. ]<br/><br/>- 성기, 음모, 항문의 노출을 포함한 게시글<br/>- 성기, 음모, 항문에 대한 성행위가 노골적으로 묘사되거나 관련 내용을 포함한 게시글<br/>- 반인륜적 성행위(시간, 수간, 스와핑) 및 이상 성행위가 묘사되거나 관련 내용을 포함한 게시글<br/>- 아동청소년을 성적 유희의 대상으로 직접적으로 묘사하거나 관련 내용을 포함한 게시글' , '2022-11-02', default);
INSERT INTO notice VALUES (120, 1, 'NOTICE', '[공지] 양파마켓 서비스 운영정책 개정 안내', '안녕하세요. 양파마켓입니다. 양파마켓 서비스 운영정책 개정 안내드립니다.<br/><br/> <p style="font-weight: bold;">01. 운영 정책 개정 사항</p><br/> • 상품이미지, 상품명, 상품설명, 양파채팅에 외부 채널로의 결제 및 대화를 유도하는 행위는 금지됩니다.<br/>• 도매판매 또는 대량 주문/발주를 받는 상품 판매를 금지합니다.<br/>• 사기 또는 아이디 도용은 무기한 활동 정지됩니다.<br/>• 다른 회원을 공격하거나 기분을 불쾌하게 하는 행위를 했을 시 이용에 제재가 따르니 유의하시기 바랍니다.<br/>' , '2022-11-02', default);
INSERT INTO notice VALUES (121, 1, 'NOTICE', '[공지] 양파마켓 이용약관 개정 안내', '안녕하세요. 양파마켓입니다.<br/><br/> 양파마켓 서비스를 이용해주시는 회원분들께 감사드리며, 예정된 이용약관 개정에 대한 주요 내용을 안내드리오니 서비스 이용에 참고하시기 바랍니다.<br/><br/> <i class="fa-regular fa-flag"></i>이용약관 개정일정<br/><br/> - 개정 이용약관 안내 기간<br/>&nbsp;&nbsp; 2022년 10월 1일 ~ 2022년 11월 8일<br/><br/> - 개정 시행일<br/> &nbsp;&nbsp; 안내기간에 따른 날짜로 시행하게됩니다.' , '2022-11-03', default);
INSERT INTO notice VALUES (122, 1, 'NOTICE', '양파마켓 채팅이용 안내', '채팅을 이용하려는 고객님들께 알려드립니다<br/> <p style="font-weight: bold; color: ">01. 채팅을 하고싶은 상대의 게시물에서 채팅방에 진입합니다.</p><p style="font-weight: bold; color: ">02. 채팅방 하단에서 [''+'' 버튼 > 사진 버튼]을 선택하여 내 pc에 저장된 사진을 올리면 됩니다.</p><br/> * 아래 사진 참고 *<br/>' , '2022-11-05', default);
INSERT INTO notice VALUES (123, 1, 'NOTICE', '양파마켓 이용정책을 확인하세요', '안녕하세요. 양파마켓입니다. 양파마켓 홈페이지 이용정책을 안내해 드리니 아래의 항목을 준수해주시기 바랍니다.<br/><br/> <p style="font-weight: bold; color: #e4606d;">01. 거래 제한 품목 등록 금지</p>- 거래 제한 품목 등록 시 게시글 무통보 삭제 및 양파마켓 이용제재 처리됩니다.<br/><br/><p style="font-weight: bold; color: #e4606d;">02. 욕설, 비매너 행위 금지</p>- 다른 회원의 거래를 악의적으로 방해하는 행위 및 비하, 욕설이 포함된 게시글 작성을 금지합니다. 양파마켓은 따뜻한 거래 문화를 만들기 위해 노력하고 있습니다. 이에 동참해주시기 바랍니다.<br/><br/><p style="font-weight: bold; color: #e4606d;">03. 도배글, 스팸글 금지</p>- 지속적으로 노출되는 도배 게시물, 스팸성 홍보 등의 행위는 금지합니다.<br/><br/><p style="font-weight: bold; color: #e4606d;">04. 신고에 따른 계정 정치</p>- 신고가 처리되면 무통보 이용정지가 되오니 이를 유의하여 건전한 마켓이용을 권장합니다.' , '2022-11-02', default);
INSERT INTO notice VALUES (124, 1, 'NOTICE', '[공지] 양파마켓 개인정보 처리방침', '<p style="color: #4c4c4c; font-weight: bold;">안녕하세요. 양파마켓입니다.</p> <p style="color: #003eff; font-weight: bold;"><i class="fa-solid fa-check"></i> 양파마켓을 이용하는 고객님들께 알립니다.</p> 아래의 안내 사항을 읽어주세요<br/><br/>' , '2022-11-03', default);
INSERT INTO notice_image VALUES (500, 124, 'onionNotice1.png');
INSERT INTO notice_image VALUES (501, 124, 'onionNotice2.png');
INSERT INTO notice_image VALUES (502, 122, 'chatEx1.png');
INSERT INTO inquiry VALUES (70, 6, '회원정보', '로그인', '로그인이 안돼요', '비밀번호를 몰라요', '2022-09-11', default, 1);
INSERT INTO inquiry VALUES (71, 6, '거래', '거래내역확인', '거래가 끝나면 어디서 봐요?', '거래가 끝나면 어디서 봐요?', '2022-09-12', default, 1);
INSERT INTO inquiry VALUES (72, 6, '기타서비스', '경매이용', '입찰가를 바꾸고싶어요', '입찰가를 바꾸고싶어요', '2022-09-13', default, 1);
INSERT INTO inquiry VALUES (73, 6, '거래', '거래확정,후기', '후기를 남기려고 하는데 어디서 쓰나요?', '후기는 어디서', '2022-09-18', default, 1);
INSERT INTO inquiry VALUES (74, 6, '회원정보', '아이디,비밀번호', '아이디 변경 가능해요?', '아이디 변경 가능해요?', '2022-09-21', default, 1);
INSERT INTO inquiry VALUES (75, 6, '기타서비스', '채팅이용', '채팅 처음써봐요', '채팅 처음써봐요', '2022-09-22', default, 1);
INSERT INTO inquiry VALUES (80, 6, '거래', '거래내역확인', '거래가 완료되면 어디서 확인하나요?', '직거래로 판매자와 거래를 완료하면 어디서 볼 수 있나요?', '2022-09-24', 'complete', 1);
INSERT INTO answer VALUES (70, 80, 1, '[메인페이지] - [거래내역] - [구매목록]탭에서 확인하실 수 있습니다.<br/><br/> 더 궁금하신 사항이 있으시다면 양파마켓 고객 센터로 연락주세요', '2022-09-27');
INSERT INTO inquiry VALUES (81, 5, '거래', '상품찾기', '제가 올린 상품이 블라인드 되었어요', '올린지 하루만에 블라인드 처리되었는데 납득이 가지 않습니다.<br/> 블라인드 상태를 해지해주세요', '2022-09-30', 'complete', 0);
INSERT INTO answer VALUES (71, 81, 1, '회원님의 게시글이 여러번 신고접수 되었고 합당한 사유로 처리가 되었습니다.<br/>이에 해당 상품이 블라인드 처리 되었습니다.<br/> 더 궁금하신 점이 있다면 고객센터로 연락해주세요.<br/>', '2022-09-30');
INSERT INTO inquiry VALUES (82, 4, '회원정보', '아이디,비밀번호', '비밀번호가 생각이 안나요!', '비밀번호를 몰라서 새로 회원가입했습니다.<br/> 기존 계정을 다시 쓰고 싶은데 어떻게 찾을 수 있나요?<br/>', '2022-10-03', 'complete', 0);
INSERT INTO answer VALUES (72, 82, 1, '로그인 화면에서 비밀번호 찾기를 이용해 찾을 수 있습니다.<br/> <p style="color: #4c4c4c; font-weight: bold; color: #e4606d;">회원가입 시 등록한 이메일 주소를 입력하면 해당 이메일로 임시 비밀번호가 발급됩니다.</p><br/> 그 다음 마이페이지에서 회원정보 수정을 통해 비밀번호를 변경할 수 있습니다. ', '2022-10-03');
INSERT INTO inquiry VALUES (83, 4, '기타서비스', '양파페이,서비스', '페이 충전하는 방법을 몰라요', '양파페이를 충전해서 사용하고 싶은데 어떻게 충전하는지 모르겠어요', '2022-10-10', 'complete', 1);
INSERT INTO answer VALUES (73, 83, 1, '홈페이지 상단 우측에 [양파페이 잔액]을 누르면 충전 페이지로 들어갑니다.<br/><br/> 원하는 금액을 선택하고 충전하면 됩니다.', '2022-10-11');
INSERT INTO inquiry VALUES (84, 3, '기타서비스', '경매이용', '경매를 이용해보고 싶어요', '근데 이용방법을 몰라요. 어떻게 하는지 알려주시면 감사하겠습니다.', '2022-10-15', 'complete', 0);
INSERT INTO answer VALUES (74, 84, 1, '상품 목록에서 AUCTION이 붙은 상품을 클릭하면 경매 이용 가능합니다.<br/> 경매에 참여하시려면 입찰가를 입력하고, <a style="color: #4c4c4c;>입찰하기</a>를 클릭하면 됩니다.', '2022-10-16');
INSERT INTO inquiry VALUES (85, 3, '거래', '거래방법', '가입하고 처음써보는데 어떻게해요?', '직거래만 가능한가요? 거래 방법 알려주세요. ', '2022-10-20', default, 1);
INSERT INTO inquiry VALUES (86, 2, '거래', '상품찾기', '봐둔 상품이 있는데 찾고싶어요', '며칠 전에 봐둔 상품을 찾고싶어요', '2022-10-23', 'complete', 0);
INSERT INTO answer VALUES (76, 86, 1, '상단의 검색탭 및 카테고리 메뉴를 통해 찾으실 수 있습니다. <br/>원하는 상품이 있다면 [찜하기] 또는 [채팅]을 이용하기를 바랍니다.', '2022-10-24');
INSERT INTO inquiry VALUES (87, 2, '기타서비스', '경매이용', '입찰되는지는 어떻게 알아요?', '어디서 알려줘요?', '2022-10-30', default, 1);
INSERT INTO inquiry VALUES (88, 2, '기타서비스', '양파페이,포인트', '페이와 포인트를 같이 쓰고싶어요', '한번에 같이 사용 가능한가요?', '2022-11-01', default, 1);
INSERT INTO inquiry VALUES (89, 3, '회원정보', '회원가입/수정', '프로필 사진을 변경하고 싶어요', '원하는 사진으로 변경하고 싶은데 어떻게 하는지 알려주세요', '2022-11-02', 'complete', 1);
INSERT INTO answer VALUES (79, 89, 1, '[마이페이지] - [사진변경] 탭을 통해 원하는 사진으로 변경 가능합니다.', '2022-11-03');
INSERT INTO inquiry VALUES (90, 2, '기타서비스', '채팅이용', '채팅으로 거래예약을 하는건가요?', '채팅 처음 써보는데 사용하는 법을 몰라요', '2022-11-05', 'complete', 1);
INSERT INTO answer VALUES (80, 90, 1, '구매를 원하는 상품 페이지에 들어가서 [채팅하기]를 클릭하면 판매자와 대화하실 수 있습니다. <br/> 채팅기록은 남아있으니 원할때마다 확인 가능합니다.', '2022-11-05');
INSERT INTO answer VALUES (81, 90, 1, '채팅으로는 상품과 거래에 대한 질문을 나눌 수 있습니다.<br/> 상품 상세페이지의 구매하기를 통해 주문을 진행해주세요', '2022-11-06');

commit;
