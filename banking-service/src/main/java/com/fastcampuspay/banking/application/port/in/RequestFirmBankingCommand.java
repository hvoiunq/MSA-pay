package com.fastcampuspay.banking.application.port.in;

import com.fastcampuspay.common.validator.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)  //super까지 포함해서 hashing할건지
public class RequestFirmBankingCommand extends SelfValidating<RequestFirmBankingCommand> {

    @NotNull
    private String fromBankName;
    @NotNull
    private String fromBankAccountNumber;
    @NotNull
    private String toBankName;
    @NotNull
    private String toBankAccountNumber;
    @NotNull
    private int moneyAccount;

    public RequestFirmBankingCommand(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAccount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAccount = moneyAccount;

        this.validateSelf();
    }
}
