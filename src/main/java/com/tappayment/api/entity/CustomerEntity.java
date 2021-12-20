package com.tappayment.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CustomerEntity {
    /**
     * customer Id
     */
    @Id
    private String id;
    /**
     * wallet Id for customer
     */
    private String walletId;
    /**
     * Total balance for user
     */
    private BigDecimal totalBalance;

    public CustomerEntity(String id, String walletId, BigDecimal totalBalance) {
        this.id = id;
        this.walletId = walletId;
        this.totalBalance = totalBalance;
    }
}
