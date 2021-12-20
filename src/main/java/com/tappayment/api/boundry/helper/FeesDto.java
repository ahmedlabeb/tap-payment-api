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
public class FeesDto {
    /**
     * Fees amount
     */
    @NotNull(message="amount cannot be missing")
    private BigDecimal amount;
    /**
     * Fees currency
     */
    @NotNull(message="currency cannot be missing")
    @NotEmpty(message="currency cannot be empty")
    @NotBlank(message="currency must contain at least one non whitespace character")
    private String currency;
}
