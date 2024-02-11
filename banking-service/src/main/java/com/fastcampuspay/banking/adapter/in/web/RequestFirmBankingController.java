package com.fastcampuspay.banking.adapter.in.web;

import com.fastcampuspay.banking.adapter.in.web.dto.request.RequestFirmBankingRequest;
import com.fastcampuspay.banking.adapter.in.web.dto.response.RequestFirmBankingResponse;
import com.fastcampuspay.banking.application.port.in.RequestFirmBankingCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmBankingUseCase;
import com.fastcampuspay.banking.domain.FirmBankingRequest;
import com.fastcampuspay.common.annotation.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {
    private final RequestFirmBankingUseCase requestFirmBankingUseCase;

    @PostMapping(path = "/banking/firm-banking/request")
    ResponseEntity<RequestFirmBankingResponse> RequestFirmBanking(@RequestBody RequestFirmBankingRequest request) {

        RequestFirmBankingCommand command = request.toCommand(request);
        FirmBankingRequest firmBankingRequest = requestFirmBankingUseCase.reguestFirmBanking(command);
        RequestFirmBankingResponse response = RequestFirmBankingResponse.of(firmBankingRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}

