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
DROP TABLE wish PURGE;

DROP TABLE chat PURGE;
DROP TABLE chatroom PURGE;

DROP TABLE member_category PURGE;

DROP TABLE product_tag PURGE;
DROP TABLE tag PURGE;
DROP TABLE bidding PURGE;
DROP TABLE product_image PURGE;
DROP TABLE product PURGE;

DROP TABLE category PURGE;
DROP TABLE orders PURGE;

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
	role				VARCHAR2(50)    DEFAULT 'ROLE_USER',
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
	member_image_name	VARCHAR2(50)    NULL,
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
	town_id             NUMBER        	NOT NULL,
	member_id           NUMBER        	NOT NULL,
	coordinate_id       NUMBER        	NOT NULL,
	CONSTRAINT PK_TOWN PRIMARY KEY (town_id),
	CONSTRAINT FK_TOWN_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_TOWN_COORDINATE_ID FOREIGN KEY (coordinate_id) REFERENCES coordinate(coordinate_id)
);

CREATE TABLE orders (
    order_id	    	NUMBER	        NOT NULL,
    member_id	    	NUMBER          NOT NULL,
    order_num           CHAR(15)        NOT NULL,
    imp_uid             VARCHAR2(20)    NOT NULL,
    order_payment       NUMBER          NOT NULL,
    order_state     	VARCHAR2(10)    DEFAULT 'ORDER',
    order_date      	DATE            DEFAULT SYSDATE,
    modified_date   	DATE            DEFAULT NULL,
    CONSTRAINT PK_ORDERS PRIMARY KEY (order_id),
    CONSTRAINT FK_ORDERS_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
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
	order_id            NUMBER          NULL,
	subject             VARCHAR2(255)   NULL,
	content             VARCHAR2(255)   NULL,
	price               NUMBER          NULL,
    representative_image VARCHAR2(100)   NULL,
	upload_date         DATE            DEFAULT SYSDATE,
	update_date         DATE            DEFAULT NULL,
	auction_deadline    DATE            NULL,
	view_count          NUMBER          DEFAULT 0,
	product_progress    VARCHAR2(20)    NULL,
	pay_status          VARCHAR2(20)    NULL,
	blind_status        VARCHAR2(20)    NULL,
	CONSTRAINT PK_PRODUCT PRIMARY KEY (product_id),
	CONSTRAINT FK_PRODUCT_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_PRODUCT_TOWN_ID FOREIGN KEY (town_id) REFERENCES town(town_id),
    CONSTRAINT FK_PRODUCT_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES  category(category_id),
	CONSTRAINT FK_PRODUCT_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(order_id)
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

CREATE TABLE tag (
	tag_id      		NUMBER        	NOT NULL,
	tag_name    		VARCHAR2(10)  	NOT NULL,
	tag_count   		NUMBER        	NOT NULL,
	CONSTRAINT PK_TAG PRIMARY KEY (tag_id)
);

CREATE TABLE product_tag (
	product_tag_id  	NUMBER  		NOT NULL,
	tag_id          	NUMBER  		NOT NULL,
	product_id      	NUMBER  		NOT NULL,
	CONSTRAINT PK_PRODUCT_TAG PRIMARY KEY (product_tag_id),
	CONSTRAINT FK_PRODUCT_TAG_TAG_ID FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
	CONSTRAINT FK_PRODUCT_TAG_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE member_category (
	member_category_id  NUMBER  		NOT NULL,
	member_id           NUMBER  		NOT NULL,
	category_id         NUMBER  		NOT NULL,
	CONSTRAINT PK_MEMBER_CATEGORY PRIMARY KEY (member_category_id),
	CONSTRAINT FK_MC_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_MC_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES category(category_id)
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
	message         	VARCHAR2(600)   NOT NULL,
	chat_image_name 	VARCHAR2(255)   NULL,
	read_or_not      	VARCHAR2(10)    NOT NULL,
	sending_time    	DATE            DEFAULT SYSDATE,
	CONSTRAINT PK_CHAT PRIMARY KEY (chat_id),
	CONSTRAINT FK_CHAT_CHATROOM_ID  FOREIGN KEY (chatroom_id) REFERENCES chatroom(chatroom_id)
);

CREATE TABLE wish (
	wish_id         	NUMBER  		NOT NULL,
	member_id       	NUMBER  		NOT NULL,
	product_id      	NUMBER  		NOT NULL,
	CONSTRAINT PK_WISH PRIMARY KEY (wish_id),
	CONSTRAINT FK_WISH_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_WISH_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE delivery (
	order_id	    	NUMBER          NOT NULL,
	postcode	    	CHAR(5)	        NOT NULL,
	address         	VARCHAR2(255)   NOT NULL,
	detail_address  	VARCHAR2(255)   NOT NULL,
	extra_address   	VARCHAR2(255)   NOT NULL,
	delivery_cost   	NUMBER          NOT NULL,
	request         	VARCHAR2(255)   NULL,
	CONSTRAINT PK_DELIVERY PRIMARY KEY (order_id),
	CONSTRAINT FK_DELIVERY_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE complain (
	complain_id         NUMBER          NOT NULL,
	member_id       	NUMBER          NOT NULL,
	product_id          NUMBER          NULL,
	chatroom_id         NUMBER          NULL,
	complain_type       VARCHAR2(20)	NOT NULL,
	complain_date       DATE            DEFAULT SYSDATE,
	complain_content    VARCHAR2(255)	NOT NULL,
	status              VARCHAR2(10)	DEFAULT 'wait',
	CONSTRAINT PK_COMPLAIN PRIMARY KEY (complain_id),
	CONSTRAINT FK_COMPLAIN_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
	CONSTRAINT FK_COMPLAIN_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id),
	CONSTRAINT FK_COMPLAIN_CHATROOM_ID FOREIGN KEY (chatroom_id) REFERENCES chatroom(chatroom_id)
);

CREATE TABLE review (
	review_id	        NUMBER	        NOT NULL,
	order_id	        NUMBER	        NOT NULL,
    member_id       	NUMBER          NOT NULL,
	review_content	    VARCHAR2(255)	NOT NULL,
	grade	            NUMBER          NOT NULL,
	review_date	        DATE            DEFAULT SYSDATE,
    sales_id            NUMBER          NOT NULL,
	CONSTRAINT PK_REVIEW PRIMARY KEY (review_id),
	CONSTRAINT FK_REVIEW_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(order_id),
    CONSTRAINT FK_REVIEW_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);
CREATE TABLE review_image(
    review_image_id     NUMBER          NOT NULL,
    review_id           NUMBER          NOT NULL,
    original_file_name  VARCHAR2(30)    NOT NULL,
    store_image_name    VARCHAR2(100)   NOT NULL,
    CONSTRAINT PK_REVIEW_IMAGE PRIMARY KEY (review_image_id),
    CONSTRAINT FK_REVIEW_IMAGE_REVIEW_ID FOREIGN KEY (review_id) REFERENCES review(review_id)
);

CREATE TABLE inquiry (
	inquiry_id	    	NUMBER	        NOT NULL,
	member_id	    	NUMBER	        NOT NULL,
	inquiry_type        VARCHAR2(30)	NOT NULL,
	detail_type         VARCHAR2(30)	NOT NULL,
	inquiry_subject     VARCHAR2(50)	NOT NULL,
	inquiry_content     VARCHAR2(255)	NOT NULL,
	inquiry_date    	DATE            DEFAULT SYSDATE,
	status          	VARCHAR2(10)	DEFAULT 'wait',
	secret              CHAR(1)         CONSTRAINT review_image_secret_CK check ( secret = '0' or secret = '1'),
	CONSTRAINT PK_INQUIRY PRIMARY KEY (inquiry_id),
	CONSTRAINT FK_INQUIRY_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE answer (
	answer_id	    	NUMBER	        NOT NULL,
	inquiry_id	    	NUMBER	        NOT NULL,
	member_id	    	NUMBER	        NOT NULL,
	answer_content      VARCHAR2(255)	NOT NULL,
	answer_date     	DATE            DEFAULT SYSDATE,
	CONSTRAINT PK_ANSWER PRIMARY KEY (answer_id),
	CONSTRAINT FK_ANSWER_INQUIRY_ID FOREIGN KEY (inquiry_id) REFERENCES inquiry(inquiry_id),
	CONSTRAINT FK_ANSWER_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE notice (
	notice_id        	NUMBER          NOT NULL,
	member_id        	NUMBER          NOT NULL,
	notice_type      	VARCHAR2(20)    NOT NULL,
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


INSERT INTO coordinate VALUES(1,'서울특별시 강남구 역삼1동','37.4954841','127.0333574');
INSERT INTO coordinate VALUES(2,'서울특별시 강남구 역삼2동','37.4959674','127.0468034');
INSERT INTO coordinate VALUES(3,'서울특별시 강남구 논현1동','37.5115706 ','127.028461');
INSERT INTO coordinate VALUES(4,'서울특별시 강남구 논현2동','37.517342','127.037213');
INSERT INTO coordinate VALUES(5,'서울특별시 강남구 삼성1동','37.5144424','127.062532');
INSERT INTO coordinate VALUES(6,'서울특별시 강남구 삼성2동','37.5112','127.04595');
INSERT INTO coordinate VALUES(7,'서울특별시 강남구 대치1동','37.4931821','127.0567047');
INSERT INTO coordinate VALUES(8,'서울특별시 강남구 대치2동','37.5022848','127.0642072');
INSERT INTO coordinate VALUES(9,'서울특별시 강남구 대치4동','37.4997415','127.0579127');
INSERT INTO coordinate VALUES(10,'서울특별시 강남구 도곡1동','37.488238','127.0390246');
INSERT INTO coordinate VALUES(11,'서울특별시 강남구 도곡2동','37.4837425','127.0464338');
INSERT INTO coordinate VALUES(12,'서울특별시 강남구 청담동','37.525107','127.049291');
INSERT INTO coordinate VALUES(13,'서울특별시 강남구 신사동','37.5240101','127.0227814');
INSERT INTO coordinate VALUES(14,'서울특별시 강남구 압구정동','37.530642','127.030713');
INSERT INTO coordinate VALUES(15,'서울특별시 강남구 개포1동','37.4827409','127.055737');
INSERT INTO coordinate VALUES(16,'서울특별시 강남구 개포4동','37.4771404','127.0497486');
INSERT INTO coordinate VALUES(17,'서울특별시 강남구 일원1동','37.491839','127.0879629');
INSERT INTO coordinate VALUES(18,'서울특별시 강남구 일원2동','37.4922048','127.0737224');
INSERT INTO coordinate VALUES(19,'서울특별시 강남구 일원본동','37.4833484','127.0864633');
INSERT INTO coordinate VALUES(20,'서울특별시 강남구 세곡동','37.4643683','127.1043555');
INSERT INTO coordinate VALUES(21 ,'서울특별시 송파구 잠실본동','37.5060716','127.0832037');
INSERT INTO coordinate VALUES(22 ,'서울특별시 송파구 잠실2동','37.5119488','127.088559 ');
INSERT INTO coordinate VALUES(23 ,'서울특별시 송파구 잠실3동','37.513333', '127.094375');
INSERT INTO coordinate VALUES(24 ,'서울특별시 송파구 잠실4동','37.5201089','127.1122668');
INSERT INTO coordinate VALUES(25 ,'서울특별시 송파구 잠실6동','37.518142','127.10065');
INSERT INTO coordinate VALUES(26 ,'서울특별시 송파구 잠실7동','37.5086777','127.0771146');
INSERT INTO coordinate VALUES(27 , '서울특별시 송파구 풍납1동','37.538092','127.122075');
INSERT INTO coordinate VALUES(28 ,'서울특별시 송파구 풍납2동','37.528833','127.116825');
INSERT INTO coordinate VALUES(29 ,'서울특별시 송파구 삼전동','37.502714','127.0925354');
INSERT INTO coordinate VALUES(30,'서울특별시 송파구 석촌동','37.503592','127.1037');
INSERT INTO coordinate VALUES(31,'서울특별시 송파구 송파1동','37.5062595',  '127.1093393');
INSERT INTO coordinate VALUES(32 ,'서울특별시 송파구 송파2동','37.502317',  '127.1167651');
INSERT INTO coordinate VALUES(33 ,'서울특별시 송파구 방이1동','37.510933',  '127.123925');
INSERT INTO coordinate VALUES(34 ,'서울특별시 송파구 방이2동','37.5164444',  '127.1114868');
INSERT INTO coordinate VALUES(35 ,'서울특별시 송파구 오륜동','37.515425',  '127.1343');
INSERT INTO coordinate VALUES(36 ,'서울특별시 송파구 오금동','37.5030528',  '127.1281494');
INSERT INTO coordinate VALUES(37 ,'서울특별시 송파구 가락본동','37.4955568',  '127.1217864');
INSERT INTO coordinate VALUES(38 ,'서울특별시 송파구 가락1동','37.4964898',  '127.1098653');
INSERT INTO coordinate VALUES(39 ,'서울특별시 송파구 가락2동','37.4987054',  '127.1266714');
INSERT INTO coordinate VALUES(40 ,'서울특별시 송파구 문정1동','37.4900982',  '127.1241719');
INSERT INTO coordinate VALUES(41,'서울특별시 송파구 문정2동','37.489823',    '127.1108107');
INSERT INTO coordinate VALUES(42,'서울특별시 송파구 장지동','37.4785',      '127.1354');
INSERT INTO coordinate VALUES(43,'서울특별시 송파구 거여1동','37.4969643',   '127.1432324');
INSERT INTO coordinate VALUES(44,'서울특별시 송파구 거여2동 ','37.4935803',   '127.1468764');
INSERT INTO coordinate VALUES(45 ,'서울특별시 송파구 마천1동','37.4960195',  '127.1499724');
INSERT INTO coordinate VALUES(46 ,'서울특별시 송파구 마천2동','37.4968477',  '127.1485193');
INSERT INTO coordinate VALUES(47 ,' 서울특별시 송파구 위례동','37.4811656',  '127.1439378');
INSERT INTO Coordinate VALUES(48 ,' 서울특별시 강동구 암사1동','37.551508','  127.132663');
INSERT INTO Coordinate VALUES( 49,' 서울특별시 강동구 암사2동','37.5517481','  127.1272074');
INSERT INTO Coordinate VALUES( 50,'서울특별시 강동구 암사3동','37.5549912','  127.1408249');
INSERT INTO Coordinate VALUES( 51 ,'서울특별시 강동구 고덕1동','37.5572593', '127.1515382');
INSERT INTO Coordinate VALUES( 52,'서울특별시 강동구 고덕2동','37.5605',     '127.16435 ');
INSERT INTO Coordinate VALUES( 53,'서울특별시 강동구 강일동','37.5649783',  '127.173909 ');
INSERT INTO Coordinate VALUES( 54,'서울특별시 강동구 명일1동','37.5512453',  '127.1443656');
INSERT INTO Coordinate VALUES( 55 ,'서울특별시 강동구 명일2동','37.546366',  '127.1513427');
INSERT INTO Coordinate VALUES( 56 ,'서울특별시 강동구 천호1동','37.5450159', '127.1368066');
INSERT INTO Coordinate VALUES( 57 ,'서울특별시 강동구 천호2동','37.5435257', '127.1254351');
INSERT INTO Coordinate VALUES( 58,'서울특별시 강동구 천호3동','37.5361455',  '127.1332269');
INSERT INTO Coordinate VALUES( 59,'서울특별시 강동구 길동','37.5345598',  '127.1426791');
INSERT INTO Coordinate VALUES( 60 ,'서울특별시 강동구 상일1동','37.5506614', '127.1649058');
INSERT INTO Coordinate VALUES( 61,'서울특별시 강동구 상일2동','37.5499518',  '127.1758801');
INSERT INTO Coordinate VALUES( 62,' 서울특별시 강동구 성내1동','37.5304417',  '127.122425');
INSERT INTO Coordinate VALUES( 63,' 서울특별시 강동구 성내2동','37.532425',  '127.129563');
INSERT INTO Coordinate VALUES( 64,' 서울특별시 강동구 둔촌1동','37.5333656','  127.1419851');
INSERT INTO Coordinate VALUES( 65 ,'서울특별시 강동구 둔촌2동','37.5332885',' 127.1419221');




INSERT INTO category VALUES(1,'디지털/가전','');

INSERT INTO category VALUES(2,'모바일',1);
INSERT INTO category VALUES(3,' 가전제품',1);
INSERT INTO category VALUES(4,'오디오/영상/관련기기',1);
INSERT INTO category VALUES(5,'PC/노트북',1);
INSERT INTO category VALUES(6,'게임/타이틀',1);
INSERT INTO category VALUES(7,'카메라/DSLR',1);
INSERT INTO category VALUES(8,'PC부품/저장장치',1);


select * from product where CATEGORY_ID  BETWEEN   15 AND 21;

select * from product where CATEGORY_ID =15;

--------------------------------------------------------------

INSERT INTO category VALUES(9,'가구/인테리어','');

INSERT INTO category VALUES(10,'가구',9);
INSERT INTO category VALUES(11,'인테리어',9);


--------------------------------------------------------------

INSERT INTO category VALUES(12,'생활/가공식품','');

INSERT INTO category VALUES(13,'주방용품',12);
INSERT INTO category VALUES(14,'생활용품',12);
INSERT INTO category VALUES(15,'식품',12);
INSERT INTO category VALUES(16,'산업용품',12);
--------------------------------------------------------------
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



--------------------------------------------------------------
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


--------------------------------------------------------------
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
--------------------------------------------------------------
INSERT INTO category VALUES(57,'가방/잡화','');

INSERT INTO category VALUES(58,'남성가방',57);
INSERT INTO category VALUES(59,'여성가방',57);
INSERT INTO category VALUES(60,'여행용',57);
--------------------------------------------------------------
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


--------------------------------------------------------------
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


--------------------------------------------------------------
INSERT INTO category VALUES(86,'게임/음반','');

INSERT INTO category VALUES(87,'CD/DVD/LP',86);
INSERT INTO category VALUES(88,'악기',86);
INSERT INTO category VALUES(89,'게임CD',86);


--------------------------------------------------------------
INSERT INTO category VALUES(90,'도서/티켓','');

INSERT INTO category VALUES(91,'도서',90);
INSERT INTO category VALUES(92,'문구',90);
INSERT INTO category VALUES(93,'기프티콘/쿠폰',90);
INSERT INTO category VALUES(94,'상품권',90);
INSERT INTO category VALUES(95,'티켓',90);

--------------------------------------------------------------
INSERT INTO category VALUES(96,'반려동물용품','');

INSERT INTO category VALUES(97,'강아지 용품',96);
INSERT INTO category VALUES(98,'강아지 사료/간식',96);
INSERT INTO category VALUES(99,'기타(강아지)',96);
INSERT INTO category VALUES(100,'고양이 용품',96);
INSERT INTO category VALUES(101,'고양이 사료/간식',96);
INSERT INTO category VALUES(102,'기타(고양이)',96);
INSERT INTO category VALUES(103,'기타(반려동물 용품)',96);
INSERT INTO category VALUES(104,'기타(반려동물 사료/간식)',96);

--------------------------------------------------------------
INSERT INTO category VALUES(105,'식물','');

INSERT INTO category VALUES(106,'꽃',105);
INSERT INTO category VALUES(107,'다육이/선인장',105);
INSERT INTO category VALUES(108,'관상수/나무',105);
INSERT INTO category VALUES(109,'허브',105);
INSERT INTO category VALUES(110,'난초',105);
INSERT INTO category VALUES(111,'채소/과일',105);
INSERT INTO category VALUES(112,'수경식물',105);
INSERT INTO category VALUES(113,'에어플랜트',105);


--------------------------------------------------------------
INSERT INTO category VALUES(114,'기타','');
INSERT INTO category VALUES(115,'기타',114);