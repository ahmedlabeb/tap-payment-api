package com.tappayment.api.control;

import com.tappayment.api.boundry.helper.*;
import com.tappayment.api.boundry.mapper.WalletDataMapper;
import com.tappayment.api.entity.CustomerEntity;
import com.tappayment.api.entity.FeesEntity;
import com.tappayment.api.entity.WalletEntity;
import com.tappayment.api.entity.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @InjectMocks
    private WalletService walletService;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private WalletDataMapper walletDataMapper;

    @Mock
    private CustomerService customerService;

    @Test
    public void testTopupCustomerWalletWithoutFees(){
        UUID transactionId = UUID.randomUUID();
        CustomerEntity customerEntity = buildCustomerEntity();
        TopupRequestDto topupRequestDto = buildTopupRequestDtoWithoutFees();
        WalletEntity walletEntity = buildWalletEntityWithoutFees(topupRequestDto,transactionId);
        TopupResponseDto topupResponseDto = buildTopupResponseDtoWithoutFees(topupRequestDto,walletEntity,transactionId);
        lenient().when(walletRepository.save(walletEntity)).thenReturn(walletEntity);
        lenient().when(customerService.topupCustomer(buildTopupRequestDtoWithoutFees())).thenReturn(customerEntity);
        lenient().when(walletDataMapper.toEntites(topupRequestDto)).thenReturn(walletEntity);
        lenient().when(walletDataMapper.toDto(walletEntity)).thenReturn(topupResponseDto);
        TopupResponseDto expectedTopupResponse = walletService.topupCustomerWallet(topupRequestDto);
        assertEquals(expectedTopupResponse.getAmount(),topupResponseDto.getAmount());
        assertEquals(expectedTopupResponse.getBalanceDto().getTotalAmount(),customerEntity.getTotalBalance());
        assertNull(expectedTopupResponse.getFeesDto());
    }


    @Test
    public void testTopupCustomerWalletWithFees(){
        UUID transactionId = UUID.randomUUID();
        CustomerEntity customerEntity = buildCustomerEntity();
        TopupRequestDto topupRequestDto = buildTopupRequestDto();
        WalletEntity walletEntity = buildWalletEntityWithFees(topupRequestDto,transactionId);
        TopupResponseDto topupResponseDto = buildTopupResponseDto(topupRequestDto,walletEntity,transactionId);
        lenient().when(walletRepository.save(walletEntity)).thenReturn(walletEntity);
        lenient().when(customerService.topupCustomer(buildTopupRequestDto())).thenReturn(customerEntity);
        lenient().when(walletDataMapper.toEntites(topupRequestDto)).thenReturn(walletEntity);
        lenient().when(walletDataMapper.toDto(walletEntity)).thenReturn(topupResponseDto);
        TopupResponseDto expectedTopupResponse = walletService.topupCustomerWallet(topupRequestDto);
        assertEquals(expectedTopupResponse.getAmount(),topupResponseDto.getAmount());
        assertEquals(expectedTopupResponse.getBalanceDto().getTotalAmount(),customerEntity.getTotalBalance());
        assertEquals(expectedTopupResponse.getFeesDto().getAmount(),topupResponseDto.getFeesDto().getAmount());
    }

    private TopupResponseDto buildTopupResponseDto(TopupRequestDto topupRequestDto,WalletEntity walletEntity,
                                                   UUID transactionId) {
    return TopupResponseDto.builder().id(transactionId).creationDate(walletEntity.getCreatedAt().toEpochMilli())
            .status(StatusEnum.SUCCESS).amount(topupRequestDto.getTopupAmount()).currency(topupRequestDto.getTopupCurrency())
            .chargeId(topupRequestDto.getChargeId()).customerDto(topupRequestDto.getCustomerDto())
            .feesDto(topupRequestDto.getFeesDto())
            .balanceDto(BalanceDto.builder().totalAmount(walletEntity.getAmount()).build())
            .build();
    }

    private TopupResponseDto buildTopupResponseDtoWithoutFees(TopupRequestDto topupRequestDto,WalletEntity walletEntity,
                                                   UUID transactionId) {
        return TopupResponseDto.builder().id(transactionId).creationDate(walletEntity.getCreatedAt().toEpochMilli())
                .status(StatusEnum.SUCCESS).amount(topupRequestDto.getTopupAmount()).currency(topupRequestDto.getTopupCurrency())
                .chargeId(topupRequestDto.getChargeId()).customerDto(topupRequestDto.getCustomerDto())
                .balanceDto(BalanceDto.builder().totalAmount(walletEntity.getAmount()).build())
                .build();
    }
    private WalletEntity buildWalletEntityWithFees(TopupRequestDto topupRequestDto,UUID transactionId) {
        WalletEntity walletEntity=new WalletEntity();
        walletEntity.setAmount(topupRequestDto.getTopupAmount());
        walletEntity.setChargeId(topupRequestDto.getChargeId());
        walletEntity.setCustomer(buildCustomerEntity());
        walletEntity.setFees(buildFeesEntity());
        walletEntity.setTransactionId(transactionId);
        walletEntity.setCreatedAt(Instant.now());
        walletEntity.setPersisted(true);
        return walletEntity;
    }
    private WalletEntity buildWalletEntityWithoutFees(TopupRequestDto topupRequestDto,UUID transactionId) {
        WalletEntity walletEntity=new WalletEntity();
        walletEntity.setAmount(topupRequestDto.getTopupAmount());
        walletEntity.setChargeId(topupRequestDto.getChargeId());
        walletEntity.setCustomer(buildCustomerEntity());
        walletEntity.setTransactionId(transactionId);
        walletEntity.setCreatedAt(Instant.now());
        walletEntity.setPersisted(true);
        return walletEntity;
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
    private FeesEntity buildFeesEntity(){
        FeesEntity feesEntity=new FeesEntity();
        feesEntity.setAmount(BigDecimal.valueOf(1.2));
        feesEntity.setCurrency("AED");
        return feesEntity;
    }
}
