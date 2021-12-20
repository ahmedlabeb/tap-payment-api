package com.tappayment.api.boundry.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    /**
     * customer Id
     */
    @NotNull(message="CustomerId cannot be missing")
    @NotEmpty(message="CustomerId cannot be empty")
    @NotBlank(message="CustomerId must contain at least one non whitespace character")
    private String id;
    /**
     * wallet Id for customer
     */

    @NotNull(message="walletId cannot be missing")
    @NotEmpty(message="walletId cannot be empty")
    @NotBlank(message="walletId must contain at least one non whitespace character")
    private String walletId;
}
