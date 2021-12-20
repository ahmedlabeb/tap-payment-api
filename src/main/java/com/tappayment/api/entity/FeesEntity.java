package com.tappayment.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesEntity {
    /**
     * Fees amount
     */
    private BigDecimal amount;
    /**
     * Fees currency
     */
    private String currency;
}
