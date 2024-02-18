package com.fastcampus.remittanceservice.adapter.in.web;


import com.fastcampus.money.adapter.in.web.dto.response.MoneyChangingResDetail;
import com.fastcampus.money.application.port.in.RequestIncreaseMoneyCommand;
import com.fastcampus.money.domain.MoneyChangingRequest;
import com.fastcampus.remittanceservice.adapter.in.web.dto.request.RequestRemittanceRequest;
import com.fastcampus.remittanceservice.application.port.in.RemittanceReqUseCase;
import com.fastcampus.remittanceservice.application.port.in.RequestRemittanceCommand;
import com.fastcampus.remittanceservice.domain.Remittance;
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
public class RequestMoneyChangingController {
    private RemittanceReqUseCase remittanceReqUseCase;
    @PostMapping(path = "/remittance/request")
    Remittance requestRemittance(@RequestBody RequestRemittanceRequest request) {

        RequestRemittanceCommand command = request.toCommand(request);
        Remittance remittanceRequest = remittanceReqUseCase.requestRemittance(command);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(remittanceRequest);
    }
}
