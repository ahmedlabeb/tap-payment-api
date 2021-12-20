package com.tappayment.api.control;

import com.tappayment.api.boundry.helper.CustomerDto;
import com.tappayment.api.boundry.helper.FeesDto;
import com.tappayment.api.boundry.helper.TopupRequestDto;
import com.tappayment.api.entity.CustomerEntity;
import com.tappayment.api.entity.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testTopupCustomerExistingCustomer(){
        CustomerEntity customerEntity = buildCustomerEntity();
        lenient().when(customerRepository.findById("1")).thenReturn(Optional.of(customerEntity));
        lenient().when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        CustomerEntity updatedCustomerEntity = customerService.topupCustomer(buildTopupRequestDto());
        assertEquals(updatedCustomerEntity.getTotalBalance().floatValue(),19.2F);
    }

    @Test
    public void testTopupCustomerExistingCustomerWithoutFees(){
        CustomerEntity customerEntity = buildCustomerEntity();
        lenient().when(customerRepository.findById("1")).thenReturn(Optional.of(customerEntity));
        lenient().when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        CustomerEntity updatedCustomerEntity = customerService.topupCustomer(buildTopupRequestDtoWithoutFees());
        assertEquals(updatedCustomerEntity.getTotalBalance().floatValue(),20.4F);
    }

    @Test
    public void testTopupCustomerNotExistingCustomer(){
        lenient().when(customerRepository.findById("1")).thenReturn(Optional.empty());
        CustomerEntity customerEntity = new CustomerEntity("1", "123", BigDecimal.valueOf(9.0));
        lenient().when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        CustomerEntity updatedCustomerEntity = customerService.topupCustomer(buildTopupRequestDto());
        assertEquals(updatedCustomerEntity.getTotalBalance().floatValue(),9.0F);
    }

    @Test
    public void testTopupCustomerNotExistingCustomerWithoutFees(){
        lenient().when(customerRepository.findById("1")).thenReturn(Optional.empty());
        CustomerEntity customerEntity = new CustomerEntity("1", "123", BigDecimal.valueOf(10.2));
        lenient().when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        CustomerEntity updatedCustomerEntity = customerService.topupCustomer(buildTopupRequestDtoWithoutFees());
        assertEquals(updatedCustomerEntity.getTotalBalance().floatValue(),10.2F);
    }

    private TopupRequestDto buildTopupRequestDto() {
        return TopupRequestDto.builder().topupAmount(BigDecimal.valueOf(10.2))
                .topupCurrency("AED")
                .chargeId("1")
                .feesDto(FeesDto.builder().amount(BigDecimal.valueOf(1.2)).currency("AED").build())
                .customerDto(CustomerDto.builder().walletId("123").id("1").build())
                .build();
    }

    private TopupRequestDto buildTopupRequestDtoWithoutFees() {
        return TopupRequestDto.builder().topupAmount(BigDecimal.valueOf(10.2))
                .topupCurrency("AED")
                .chargeId("1")
                .customerDto(CustomerDto.builder().walletId("123").id("1").build())
                .build();
    }
    private CustomerEntity buildCustomerEntity(){
        CustomerEntity customerEntity=new CustomerEntity();
        customerEntity.setTotalBalance(BigDecimal.valueOf(10.2));
        customerEntity.setId("1");
        customerEntity.setWalletId("1");
        return customerEntity;
    }
}
