package com.tappayment.api.boundry.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopupRequestDto {

    @NotNull(message="topupAmount cannot be missing")
    private BigDecimal topupAmount;

    @NotNull(message="topupCurrency cannot be missing")
    @NotEmpty(message="topupCurrency cannot be empty")
    @NotBlank(message="topupCurrency must contain at least one non whitespace character")
    private String topupCurrency;

    @NotNull(message="chargeId cannot be missing")
    @NotEmpty(message="chargeId cannot be empty")
    @NotBlank(message="chargeId must contain at least one non whitespace character")
    private String chargeId;

    @NotNull(message="customerDto cannot be missing")
    private CustomerDto customerDto;

    private FeesDto feesDto;

}
