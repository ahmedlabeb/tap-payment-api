package com.tappayment.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity implements Persistable<UUID> {


    @Id
    /**
     * unique UUID for each topup transaction
     */
    private UUID transactionId;

    @CreatedDate
    private Instant createdAt;


    /**
    Topup amount
     */
    private BigDecimal amount;
    /**
     * Amount currency
     */
    private String currency;
    /**
     * charge id for deduction process
     */
    private String chargeId;
    /**
     * Customer details
     */
    private CustomerEntity customer;
    /**
     * Fees details
     */
    private FeesEntity fees;

    private boolean persisted;

    @Override
    public UUID getId() {
        return transactionId;
    }

    @Override
    public boolean isNew() {
        return !persisted;
    }
}
