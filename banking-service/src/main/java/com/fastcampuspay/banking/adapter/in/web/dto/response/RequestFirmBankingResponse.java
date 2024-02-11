package com.fastcampuspay.banking.adapter.in.web.dto.response;

import com.fastcampuspay.banking.domain.FirmBankingRequest;
import lombok.Builder;

@Builder
public record RequestFirmBankingResponse(
        int resultCode // 0:성공 1:실패
) {
    public static RequestFirmBankingResponse of(FirmBankingRequest firmBankingRequest) {
        return new RequestFirmBankingResponse(firmBankingRequest.getFirmBankingStatus());
    }
}
