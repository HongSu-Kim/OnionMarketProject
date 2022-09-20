/*
SQLPLUS system/dba;

CREATE USER onion IDENTIFIED BY a123
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP;

GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE TO onion;

CONNECT onion/a123;
*/

DROP TABLE notice_image PURGE;
DROP TABLE notice_board PURGE;
DROP TABLE answer PURGE;
DROP TABLE inquiry PURGE;
DROP TABLE review PURGE;
DROP TABLE complain PURGE;

DROP TABLE delivery PURGE;
DROP TABLE orders PURGE;
DROP TABLE wish PURGE;

DROP TABLE chat PURGE;
DROP TABLE chat_room PURGE;

DROP TABLE product_category PURGE;
DROP TABLE member_category PURGE;
DROP TABLE category PURGE;

DROP TABLE product_tag PURGE;
DROP TABLE tag PURGE;
DROP TABLE countdown PURGE;
DROP TABLE product_image PURGE;
DROP TABLE product PURGE;

DROP TABLE town PURGE;
DROP TABLE coordinate PURGE;

DROP TABLE keyword PURGE;
DROP TABLE follow PURGE;
DROP TABLE block PURGE;
DROP TABLE member PURGE;



CREATE TABLE member (
	member_id           NUMBER	        NOT NULL,
	role	            VARCHAR2(15)	NULL,
	user_id	            VARCHAR2(30)	NOT NULL,
	pwd	                VARCHAR2(40)	NOT NULL,
	nickname            VARCHAR2(40)	NOT NULL,
	name	            VARCHAR2(30)	NOT NULL,
	birth	            VARCHAR2(30)	NOT NULL,
	tel	                CHAR(11)	    NOT NULL,
	post_code           NUMBER	        NOT NULL,
	address             VARCHAR2(100)	NOT NULL,
	detail_address      VARCHAR2(100)   NOT NULL,
	extra_address       VARCHAR2(100)   NULL,
	email	            VARCHAR2(50)	NOT NULL,
	member_image_name   VARCHAR2(50)    NULL,
	cash	            NUMBER	        DEFAULT 0,
	point	            NUMBER	        DEFAULT 0,
	user_grade          NUMBER          DEFAULT 0,
	complaint_count     NUMBER          DEFAULT 0,
	CONSTRAINT PK_MEMBER PRIMARY KEY (member_id)
);


CREATE TABLE follow (
	follow_id       NUMBER	NOT NULL,
	member_id       NUMBER	NOT NULL,
	follow_target   NUMBER  NOT NULL,
	CONSTRAINT PK_FOLLOW PRIMARY KEY (follow_id, member_id),
	CONSTRAINT FK_FOLLOW_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (member_id),
	CONSTRAINT FK_FOLLOW_FOLLOW_TARGET FOREIGN KEY (follow_target) REFERENCES member (member_id)
	);

CREATE TABLE block (
	block_id        NUMBER	NOT NULL,
	member_id       NUMBER  NOT NULL,
	block_target    NUMBER  NOT NULL,
	CONSTRAINT PK_BLOCK PRIMARY KEY (block_id, member_id),
	CONSTRAINT FK_BLOCK_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member (member_id),
	CONSTRAINT FK_BLOCK_BLOCK_TARGET FOREIGN KEY (block_target) REFERENCES member (member_id)
);

CREATE TABLE keyword (
    keyword_id      NUMBER          NOT NULL,
    member_id       NUMBER          NOT NULL,
    keyword_name    VARCHAR2(255)   NULL,
    CONSTRAINT PK_KEYWORD PRIMARY KEY (keyword_id),
    CONSTRAINT FK_KEYWORD_MEMBER_ID FOREIGN KEY (member_id) REFERENCES  member(member_id)
);

CREATE TABLE coordinate (
    coordinate_id NUMBER        NOT NULL,
    town_name     VARCHAR2(10)  NOT NULL,
    longitude     VARCHAR2(20)  NOT NULL,
    latitude      VARCHAR2(20)  NOT NULL,
    CONSTRAINT PK_COORDINATE PRIMARY KEY (coordinate_id)
);

CREATE TABLE town (
    town_id        NUMBER        NOT NULL,
    member_id      NUMBER        NOT NULL,
    coordinate_id  NUMBER        NOT NULL,
    region         VARCHAR2(10)  NOT NULL,
    CONSTRAINT PK_TOWN PRIMARY KEY (town_id),
    CONSTRAINT FK_TOWN_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_TOWN_COORDINATE_ID FOREIGN KEY (coordinate_id) REFERENCES coordinate(coordinate_id)
);

CREATE TABLE product (
    product_id      NUMBER          NOT NULL,
    member_id       NUMBER          NOT NULL,
    town_id         NUMBER          NOT NULL,
    product_name    VARCHAR2(255)   NULL,
    subject         VARCHAR2(255)   NULL,
    content         VARCHAR2(255)   NULL,
    price           VARCHAR2(255)   NULL,
    upload_date     DATE            DEFAULT SYSDATE,
    update_date     DATE            DEFAULT NULL,
    view_count      NUMBER          DEFAULT 0,
    product_progress VARCHAR2(255)  NULL,
    pay_status      VARCHAR2(255)   NULL,
    auction_status  VARCHAR2(255)   NULL,
    blind_status    VARCHAR2(255)   NULL,
    CONSTRAINT PK_PRODUCT PRIMARY KEY (product_id),
    CONSTRAINT FK_PRODUCT_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_PRODUCT_TOWN_ID    FOREIGN KEY (town_id) REFERENCES town(town_id)
);

CREATE TABLE product_image (
    product_image_id   NUMBER       NOT NULL,
    product_id         NUMBER       NOT NULL,
    product_image_name VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_PRODUCT_IMAGE PRIMARY KEY (product_image_id),
    CONSTRAINT FK_PRODUCT_IMAGE_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE countdown (
    countdown_id   NUMBER  NOT NULL,
    product_id     NUMBER  NOT NULL,
    CONSTRAINT PK_COUNTDOWN PRIMARY KEY (countdown_id),
    CONSTRAINT FK_COUNTDOWN_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE tag (
    tag_id      NUMBER        NOT NULL,
    tag_name    VARCHAR2(10)  NOT NULL,
    tag_count   NUMBER        NOT NULL,
    CONSTRAINT PK_TAG PRIMARY KEY (tag_id)
);

CREATE TABLE product_tag (
    product_tag_id  NUMBER  NOT NULL,
    tag_id          NUMBER  NOT NULL,
    product_id      NUMBER  NOT NULL,
    CONSTRAINT PK_PRODUCT_TAG PRIMARY KEY (product_tag_id),
    CONSTRAINT FK_PRODUCT_TAG_TAG_ID FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
    CONSTRAINT FK_PRODUCT_TAG_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE category (
    category_id     NUMBER          NOT NULL,
    category_name   VARCHAR2(255)   NOT NULL,
    parent_id       NUMBER          NULL,
    CONSTRAINT PK_CATEGORY PRIMARY KEY (category_id)
);

CREATE TABLE member_category (
    member_category_id  NUMBER  NOT NULL,
    member_id           NUMBER  NOT NULL,
    category_id         NUMBER  NOT NULL,
    CONSTRAINT PK_MEMBER_CATEGORY PRIMARY KEY (member_category_id),
    CONSTRAINT FK_MC_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_MC_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE product_category (
    product_category_id NUMBER  NOT NULL,
    category_id         NUMBER  NOT NULL,
    product_id          NUMBER  NOT NULL,
    CONSTRAINT PK_PRODUCT_CATEGORY PRIMARY KEY (product_category_id),
    CONSTRAINT FK_PC_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES category(category_id),
    CONSTRAINT FK_PC_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE chat_room (
    chat_room_id    number          NOT NULL,
    product_id      number          NOT NULL,
    member_id       number          NOT NULL,
    state           varchar2(10)    NOT NULL,
    create_date     date            DEFAULT SYSDATE,
    modify_date     date            DEFAULT NULL,
    CONSTRAINT PK_CHAT_ROOM PRIMARY KEY (chat_room_id),
    CONSTRAINT FK_CHAT_ROOM_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_CHAT_ROOM_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE chat (
    chat_id         number          NOT NULL,
    chat_room_id    number          NOT NULL,
    message         varchar2(600)   NOT NULL,
    chat_image_name varchar2(255)   NULL,
    read_or_no      varchar2(10)    NOT NULL,
    sending_time    date            DEFAULT SYSDATE,
    CONSTRAINT PK_CHAT PRIMARY KEY (chat_id),
    CONSTRAINT FK_CHAT_CHAT_ROOM_ID  FOREIGN KEY (chat_room_id) REFERENCES chat_room(chat_room_id)
);

CREATE TABLE wish (
	wish_id         NUMBER  NOT NULL,
	member_id       NUMBER  NOT NULL,
	product_id      NUMBER  NOT NULL,
	created_date    DATE    DEFAULT SYSDATE,
    CONSTRAINT PK_WISH PRIMARY KEY (wish_id),
    CONSTRAINT FK_WISH_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_WISH_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE orders (
	order_id	    NUMBER	        NOT NULL,
	member_id	    NUMBER          NOT NULL,
	product_id	    NUMBER          NOT NULL,
	order_role	    VARCHAR2(10)    NOT NULL,
    order_state     VARCHAR2(10)    DEFAULT 'order',
	order_date      DATE            DEFAULT SYSDATE,
	modified_date   DATE            DEFAULT NULL,
    CONSTRAINT PK_ORDERS PRIMARY KEY (order_id),
    CONSTRAINT FK_ORDERS_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_ORDERS_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE delivery (
	order_id	    NUMBER          NOT NULL,
	post_code	    NUMBER	        NOT NULL,
	address         VARCHAR2(255)   NOT NULL,
	detail_address  VARCHAR2(255)   NOT NULL,
	extra_address   VARCHAR2(255)   NOT NULL,
	delivery_cost   NUMBER          NOT NULL,
	request         VARCHAR2(255)   NULL,
    CONSTRAINT PK_DELIVERY PRIMARY KEY (order_id),
    CONSTRAINT FK_DELIVERY_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE complain (
    complain_id         NUMBER          NOT NULL,
    product_id          NUMBER          NULL,
    chatroom_id         NUMBER          NULL,
    complain_type       VARCHAR(20)     NOT NULL,
    complain_date       DATE            DEFAULT SYSDATE,
    complain_user       VARCHAR(50)     NOT NULL,
    complain_content    VARCHAR(255)    NOT NULL,
    status              VARCHAR(10)     DEFAULT 'wait',
    CONSTRAINT PK_COMPLAIN PRIMARY KEY (complain_id),
    CONSTRAINT FK_COMPLAIN_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id),
    CONSTRAINT FK_COMPLAIN_CHATROOM_ID FOREIGN KEY (chatroom_id) REFERENCES chat_room(chatroom_id)
);

CREATE TABLE review (
    review_id	        NUMBER	        NOT NULL,
    order_id	        NUMBER	        NOT NULL,
    review_type	        VARCHAR(10)     NOT NULL,
    review_content	    VARCHAR(255)    NOT NULL,
    grade	            NUMBER          NOT NULL,
    reivew_date	        DATE            DEFAULT SYSDATE,
    review_image_name   VARCHAR(50)     NULL,
    CONSTRAINT PK_REVIEW PRIMARY KEY (review_id),
    CONSTRAINT FK_REVIEW_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE inquiry (
    inquiry_id	    NUMBER	        NOT NULL,
    member_id	    NUMBER	        NOT NULL,
    inquiry_type	VARCHAR(20)     NOT NULL,
    inquiry_subject	VARCHAR(30)     NOT NULL,
    inquiry_content	VARCHAR(255)    NOT NULL,
    inquiry_date    DATE            DEFAULT SYSDATE,
    status          VARCHAR(10)     DEFAULT 'wait',
    CONSTRAINT PK_INQUIRY PRIMARY KEY (inquiry_id),
    CONSTRAINT FK_INQUIRY_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE answer (
    answer_id	    NUMBER	        NOT NULL,
    inquiry_id	    NUMBER	        NOT NULL,
    member_id	    NUMBER	        NOT NULL,
    answer_subject	VARCHAR(30)     NOT NULL,
    answer_content	VARCHAR(255)    NOT NULL,
    answer_date     DATE            DEFAULT SYSDATE,
    CONSTRAINT PK_ANSWER PRIMARY KEY (answer_id),
    CONSTRAINT FK_ANSWER_INQUIRY_ID FOREIGN KEY (inquiry_id) REFERENCES inquiry(inquiry_id),
    CONSTRAINT FK_ANSWER_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE notice_board (
    notice_id        number          NOT NULL,
    member_id        number          NOT NULL,
    notice_type      varchar2(20)    NOT NULL,
    notice_subject   varchar2(255)   NOT NULL,
    notice_content   varchar2(4000)  NOT NULL,
    notice_date      date            DEFAULT SYSDATE,
    hit_count        number          DEFAULT 0,
    CONSTRAINT PK_NOTICE_BOARD PRIMARY KEY (notice_id),
    CONSTRAINT FK_NOTICE_BOARD_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE notice_image (
    notice_image_id     number          NOT NULL,
    notice_id           number          NOT NULL,
    notice_image_name   varchar2(255)   NOT NULL,
    CONSTRAINT PK_NOTICE_IMAGE PRIMARY KEY (notice_image_id),
    CONSTRAINT FK_NOTICE_IMAGE_NOTICE_ID FOREIGN KEY (notice_id) REFERENCES notice_board(notice_id)
);
