package com.youprice.onion.entity.chat;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Chatroom{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "chatroom_id")
    private Long id;//채팅방번호 PK

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; //회원번호 FK - 구매자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; //상품번호 FK - 판매자

    private ChatroomState state; //채팅방 상태 - chatting,end,delete
    private LocalDateTime createDate; //생성시간
	@Setter
    private LocalDateTime modifyDate; //수정시간(마지막 채팅 시간)


    @OneToMany(mappedBy = "chatroom")
    private List<Chat> chatList;


	public Chatroom(Member member, Product product) {
		this.member = member;
		this.product = product;
		state = ChatroomState.CHATTING;
		createDate = LocalDateTime.now();
		modifyDate = LocalDateTime.now();
	}

}
