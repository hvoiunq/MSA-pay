package com.fastcampuspay.banking.adapter.out.external.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 이전 Membership 객체가 아니다.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership { // for banking-service

    private long id;

    private String name;

    private String email;

    private String address;

    private boolean isValid;

    private boolean isCorp;

}
