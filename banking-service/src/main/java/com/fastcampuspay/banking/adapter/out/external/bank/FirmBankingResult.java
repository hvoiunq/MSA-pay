package com.fastcampuspay.banking.adapter.out.external.bank;

import lombok.Getter;

@Getter
public class FirmBankingResult {
    private int resultCode; // 0:성공 1:실패

    public FirmBankingResult(int resultCode) {
        this.resultCode = resultCode;
    }
}
