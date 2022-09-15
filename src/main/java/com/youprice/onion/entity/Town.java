package com.youprice.onion.entity;

@Entity
public class Town {
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "town_id")
        private Long id; //동네번호 PK

        @ManyToOne
        @JoinColumn(name = "coordinate_id")
        private Coordinate coordinate; //좌표번호 FK

        @ManyToOne
        @JoinColumn(name ="member_id") //회원번호 FK
        private Member member;


        private String region; //동네

}
