package com.tappayment.api.control;

import com.tappayment.api.boundry.helper.*;
import com.tappayment.api.boundry.mapper.WalletDataMapper;
import com.tappayment.api.entity.CustomerEntity;
import com.tappayment.api.entity.WalletEntity;
import com.tappayment.api.entity.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private WalletRepository walletRepository;

    private WalletDataMapper walletDataMapper;

    private CustomerService customerService;

    public WalletService(WalletRepository walletRepository, WalletDataMapper walletDataMapper, CustomerService customerService) {
        this.walletRepository = walletRepository;
        this.walletDataMapper = walletDataMapper;
        this.customerService = customerService;
    }

    public TopupResponseDto topupCustomerWallet(TopupRequestDto topupRequestDto) {
        CustomerEntity updatedCustomerEntity = customerService.topupCustomer(topupRequestDto);
        WalletEntity walletEntity = walletDataMapper.toEntites(topupRequestDto);
        WalletEntity savedWalletEntity = walletRepository.save(walletEntity);
        TopupResponseDto topupResponseDto = walletDataMapper.toDto(savedWalletEntity);
        topupResponseDto.setStatus(StatusEnum.SUCCESS);
        BalanceDto balanceDto = BalanceDto.builder().totalAmount(updatedCustomerEntity.getTotalBalance()).build();
        topupResponseDto.setBalanceDto(balanceDto);
        return topupResponseDto;
    }


}
