package com.tappayment.api.control;

import com.tappayment.api.boundry.helper.FeesDto;
import com.tappayment.api.boundry.helper.TopupRequestDto;
import com.tappayment.api.entity.CustomerEntity;
import com.tappayment.api.entity.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Topup customer total balance
     * @param topupRequestDto
     * @return
     */
    public CustomerEntity topupCustomer(TopupRequestDto topupRequestDto) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(topupRequestDto.getCustomerDto().getId());
        CustomerEntity updatedCustomerEntity = null;
        if (!optionalCustomer.isPresent()) {
            BigDecimal totalBalance = calculateCustomerTotalBalance(topupRequestDto.getFeesDto(), topupRequestDto.getTopupAmount(), BigDecimal.ZERO);
            updatedCustomerEntity = createNewCustomer(topupRequestDto.getCustomerDto().getId(),topupRequestDto.getCustomerDto().getWalletId(), totalBalance);
        } else {
            CustomerEntity customerEntity = optionalCustomer.get();
            BigDecimal totalBalance = calculateCustomerTotalBalance(topupRequestDto.getFeesDto(), topupRequestDto.getTopupAmount(), customerEntity.getTotalBalance());
            customerEntity.setTotalBalance(totalBalance);
            updatedCustomerEntity = customerRepository.save(customerEntity);
        }
        return updatedCustomerEntity;
    }

    /**
     * Calculate the total balance for the user in case we have or don't have fees
     * @param feesDto
     * @param topupAmount
     * @param customerEntityTotalBalance
     * @return
     */
    private BigDecimal calculateCustomerTotalBalance(FeesDto feesDto, BigDecimal topupAmount, BigDecimal customerEntityTotalBalance) {
        BigDecimal updatedTotalBalance;
        if (feesDto != null) {
            updatedTotalBalance = customerEntityTotalBalance.add(topupAmount.subtract(feesDto.getAmount()));
        } else {
            updatedTotalBalance = customerEntityTotalBalance.add(topupAmount);
        }
        return updatedTotalBalance;
    }

    /**
     * Create new Customer entity in DB
     * @param walletId
     * @param totalBalance
     * @return
     */
    private CustomerEntity createNewCustomer(String customerId,String walletId, BigDecimal totalBalance) {
        return customerRepository.save(new CustomerEntity(customerId,walletId, totalBalance));
    }
}
