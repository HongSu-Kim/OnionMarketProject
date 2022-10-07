package com.youprice.onion.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String postcode;//우편번호
    private String address;//주소
    private String detailAddress;//상세주소
    private String extraAddress;//참고사항

}