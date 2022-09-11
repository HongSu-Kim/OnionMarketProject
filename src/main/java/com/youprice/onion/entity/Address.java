package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String postCode;//우편번호
    private String address;//주소
    private String detailAddress;//상세주소
    private String extraAddress;//참고사항

}
