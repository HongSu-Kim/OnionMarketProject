/*
SQLPLUS system/dba;

CREATE USER onion IDENTIFIED BY a123
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP;

GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE TO onion;

CONNECT onion/a123;
*/

DROP TABLE inquiry PURGE;
DROP TABLE review PURGE;
DROP TABLE complain PURGE;
DROP TABLE delivery PURGE;
DROP TABLE orders PURGE;
DROP TABLE wish PURGE;
DROP TABLE member PURGE;



CREATE TABLE wish (
	wish_id     NUMBER,
	member_id   NUMBER  NOT NULL,
	product_id  NUMBER  NOT NULL,
	created_date DATE,
    CONSTRAINT PK_WISH PRIMARY KEY (wish_id),
    CONSTRAINT FK_WISH_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_WISH_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE orders (
	order_id	NUMBER	    NOT NULL,
	member_id	NUMBER      NOT NULL,
	product_id	NUMBER      NOT NULL,
	order_role	VARCHAR2(10) NOT NULL,
    order_state VARCHAR2(10) NOT NULL,
	order_date  DATE,
	modified_date DATE,
    CONSTRAINT PK_ORDERS PRIMARY KEY (order_id),
    CONSTRAINT FK_ORDERS_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_ORDERS_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE delivery (
	order_id	NUMBER,
	post_code	NUMBER	        NOT NULL,
	address	    VARCHAR2(255)	NOT NULL,
	detail_address VARCHAR2(255)	NOT NULL,
	extra_address VARCHAR2(255)	NOT NULL,
	request     VARCHAR2(255),
    CONSTRAINT PK_DELIVERY PRIMARY KEY (order_id),
    CONSTRAINT FK_DELIVERY_ORDER_ID FOREIGN KEY (order_id) REFERENCES member(order_id)
);

CREATE TABLE complain (
    complain_id     NUMBER          NOT NULL,
    product_id      NUMBER,
    chat_room_id    NUMBER,
    complain_type   VARCHAR2(10)    NOT NULL,
    complain_date   DATE            DEFAULT SYSDATE,
    complain_user   VARCHAR2(50)    NOT NULL,
    complain_content VARCHAR2(255)   NOT NULL,
    status           VARCHAR2(10),
    CONSTRAINT PK_COMPLAIN PRIMARY KEY (complain_id),
    CONSTRAINT FK_COMPLAIN_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES product(product_id),
    CONSTRAINT FK_COMPLAIN_CHATROOM_ID FOREIGN KEY (chatroom_id) REFERENCES chat_room(chatroom_id)
);

CREATE TABLE review (
    review_id       NUMBER      NOT NULL,
    order_id	    NUMBER      NOT NULL,
    review_type	    VARCHAR2(10) NOT NULL,
    review_content	VARCHAR2(255) NOT NULL,
    grade	        NUMBER      NOT NULL,
    review_date	    DATE,
    image_file_name	VARCHAR2(50),
    CONSTRAINT PK_REVIEW PRIMARY KEY (review_id),
    CONSTRAINT FK_REVIEW_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE inquiry (
    inquiry_id	    NUMBER      NOT NULL,
    member_id	    NUMBER      NOT NULL,
    inquiry_type	VARCHAR2(10) NOT NULL,
    inquiry_subject	VARCHAR2(255) NOT NULL,
    inquiry_content	VARCHAR2(255) NOT NULL,
    inquiry_date    DATE,
    inquiry_parent	NUMBER,
    CONSTRAINT PK_INQUIRY PRIMARY KEY (inquiry_id),
    CONSTRAINT FK_INQUIRY_MEMBER_ID FOREIGN KEY (member_id) REFERENCES member(member_id),
    CONSTRAINT FK_INQUIRY_INQUIRY_PARENT FOREIGN KEY (inquiry_parent) REFERENCES inquiry(inquiry_id)
);





