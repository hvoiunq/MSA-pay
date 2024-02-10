package com.fastcampuspay.banking.adapter.out.external.bank;

import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import com.fastcampuspay.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;


@ExternalSystemAdapter
@RequiredArgsConstructor
public class ExternalBankAccountAdapter implements RequestBankAccountInfoPort {

    @Override
    public ExternalBankAccount getBankAccountInfo(ExternalBankAccountRequest request) {
        // 실제 API 구현 불가로 임시 return
        return new ExternalBankAccount(request.getName(), request.getBankAccountNumber(), true);
    }
}
