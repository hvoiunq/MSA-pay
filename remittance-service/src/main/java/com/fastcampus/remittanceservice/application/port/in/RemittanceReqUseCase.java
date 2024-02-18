package com.fastcampus.remittanceservice.application.port.in;

import com.fastcampus.remittanceservice.domain.Remittance;

public interface RemittanceReqUseCase {
    Remittance requestRemittance(RequestRemittanceCommand requestRemittanceCommand);
}
