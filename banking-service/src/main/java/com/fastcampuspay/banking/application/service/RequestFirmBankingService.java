package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.out.external.bank.FirmBankingResult;
import com.fastcampuspay.banking.application.port.in.RequestFirmBankingCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmBankingUseCase;
import com.fastcampuspay.banking.application.port.out.external.RequestExternalFirmBankingPort;
import com.fastcampuspay.banking.application.port.out.persistence.RequestFirmBankingPort;
import com.fastcampuspay.banking.domain.FirmBankingRequest;
import com.fastcampuspay.common.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

    private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;
    private final RequestFirmBankingPort requestFirmBankingPort;

    @Override
    public FirmBankingRequest reguestFirmBanking(RequestFirmBankingCommand command) {

        // a -> b 계좌
        // 1. 요청에 대해 정보 write "요청" 상태

        UUID uuid = UUID.randomUUID();
        FirmBankingRequest firmBankingRequest = requestFirmBankingPort.createFirmBankingRequest(
                new FirmBankingRequest.FromBankName(command.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(command.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(command.getMoneyAccount()),
                new FirmBankingRequest.FirmBankingStatus(0),
                uuid
        );

        // 2. 외부 은행에 펌뱅킹 이체 요청
        FirmBankingResult firmBankingResult = requestExternalFirmBankingPort.requestExternalFirmBanking(
                new FirmBankingRequest.FromBankName(command.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(command.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(command.getToBankAccountNumber())
        );

        // 3. 이체 결과 수신, 결과에 따라 1번에서 작성한 FirmBankingRequest 정보 update
        FirmBankingRequest.FirmBankingStatus firmBankingStatus = null;
        if (firmBankingResult.getResultCode() == 0) {
            // 성공상태 업데이트
            firmBankingStatus = new FirmBankingRequest.FirmBankingStatus(1);
        } else if (firmBankingResult.getResultCode() == 1) {
            // 실패상태 업데이트
            firmBankingStatus = new FirmBankingRequest.FirmBankingStatus(2);
        } else {
            throw new IllegalArgumentException("정의하지 않는 결과상태값입니다.");
        }

        // 4. 결과 리턴
        return requestFirmBankingPort.modifyFirmBankingRequest(firmBankingStatus, uuid);
    }
}
