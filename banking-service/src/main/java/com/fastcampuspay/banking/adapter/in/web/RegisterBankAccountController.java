package com.fastcampuspay.banking.adapter.in.web;


import com.fastcampuspay.banking.adapter.in.web.dto.request.RegisterBankAccountRequest;
import com.fastcampuspay.banking.adapter.in.web.dto.response.BankAccountResponse;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.domain.BankAccount;
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
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path = "/bank/account/register")
    ResponseEntity<BankAccountResponse> registerMembership(@RequestBody RegisterBankAccountRequest request) {
        // request~~

        // request -> command

        // Usecase~~ (command를 받는 역할)

        RegisterBankAccountCommand command = request.toCommand();
        BankAccount bankAccount = registerBankAccountUseCase.registerBankAccount(command);
        if (bankAccount == null) {
            // TODO : Error Handling
            return null;
        }
        BankAccountResponse response = BankAccountResponse.of(bankAccount);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
