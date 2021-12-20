package com.tappayment.api.boundry.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopupResponseDto {
    private UUID id;
    private Long creationDate;
    private StatusEnum status;
    private BigDecimal amount;
    private String currency;
    private String chargeId;
    private CustomerDto customerDto;
    private FeesDto feesDto;
    private BalanceDto balanceDto;

}
