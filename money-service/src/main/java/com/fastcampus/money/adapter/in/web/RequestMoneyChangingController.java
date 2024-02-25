package com.fastcampus.money.adapter.in.web;


import com.fastcampus.money.adapter.in.web.dto.request.CreateMemberMoneyRequest;
import com.fastcampus.money.adapter.in.web.dto.request.IncreaseMoneyChangingRequest;
import com.fastcampus.money.adapter.in.web.dto.response.MoneyChangingResDetail;
import com.fastcampus.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.fastcampus.money.application.port.in.*;
import com.fastcampus.money.domain.MoneyChangingRequest;
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

    private final IncreaseMoneyReqUseCase increaseMoneyReqUseCase;
    private final CreateMemberMoneyUseCase createMemberMoneyUseCase;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;

    @PostMapping(path = "/money/increase")
    ResponseEntity<MoneyChangingResDetail> increaseMoneyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request) {

        RequestIncreaseMoneyCommand command = request.toCommand(request);
        MoneyChangingRequest moneyChangingRequest = increaseMoneyReqUseCase.increaseMoney(command);

        //MoneyChangingRequest -> MoneyChangingResultDetail
        MoneyChangingResDetail moneyChangingResDetail = moneyChangingRequestMapper.mapToMoneyChangingResDetail(moneyChangingRequest);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(moneyChangingResDetail);
    }

    @PostMapping(path = "/money/increase-async")
    ResponseEntity<MoneyChangingResDetail> increaseMoneyChangingRequestAync(@RequestBody IncreaseMoneyChangingRequest request) {

        RequestIncreaseMoneyCommand command = request.toCommand(request);
        MoneyChangingRequest moneyChangingRequest = increaseMoneyReqUseCase.increaseMoney(command);

        //MoneyChangingRequest -> MoneyChangingResultDetail
        MoneyChangingResDetail moneyChangingResDetail = moneyChangingRequestMapper.mapToMoneyChangingResDetail(moneyChangingRequest);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(moneyChangingResDetail);
    }

    @PostMapping(path = "money/create-member-money")
    void createMemberMoney(@RequestBody CreateMemberMoneyRequest request) {

        createMemberMoneyUseCase.CreateMemberMoney(
                CreateMemberMoneyCommand.builder()
                        .targetMembershipId(request.targetMembershipId())
                        .build());
    }

    @PostMapping(path = "/money/increase-eda")
    void increaseMoneyChangingRequestByEvent(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyReqCommand command = IncreaseMoneyReqCommand.builder()
                .membershipId(request.targetMembershipId())
                .amount(request.amount())
                .build();

        increaseMoneyReqUseCase.increaseMoneyRequestByEvent(command);
    }
}
