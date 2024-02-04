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
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {
    @NotNull
    private final long membershipId;
    @NotBlank
    private final String bankName; // (참고) 빈값 방지
    @NotNull
    private final String bankAccountNumber;
    @NotNull
    private final boolean isValid;

    public RegisterBankAccountCommand(long membershipId, String bankName, String bankAccountNumber, boolean isValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = isValid;

        this.validateSelf();
    }
}
