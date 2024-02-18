package com.fastcampus.remittanceservice.application.port.in;

import com.fastcampuspay.common.validator.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

 @Builder
@Data
@EqualsAndHashCode(callSuper = false)  //super까지 포함해서 hashing할건지
public class RequestRemittanceCommand extends SelfValidating<RequestRemittanceCommand> {

    @NotNull
    private long fromMembershipId;
    private long toMembershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int remittanceType;
    @NotBlank
    @NotNull
    private int amount;

     public RequestRemittanceCommand(long fromMembershipId, long toMembershipId, String toBankName, String toBankAccountNumber, int remittanceType, int amount) {
         this.fromMembershipId = fromMembershipId;
         this.toMembershipId = toMembershipId;
         this.toBankName = toBankName;
         this.toBankAccountNumber = toBankAccountNumber;
         this.remittanceType = remittanceType;
         this.amount = amount;
     }
 }
