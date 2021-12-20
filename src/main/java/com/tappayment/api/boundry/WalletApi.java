package com.tappayment.api.boundry;

import com.tappayment.api.boundry.helper.ResponseUtil;
import com.tappayment.api.boundry.helper.TopupRequestDto;
import com.tappayment.api.boundry.helper.TopupResponseDto;
import com.tappayment.api.control.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "Wallet Enpoint")
public class WalletApi {

    private WalletService walletService;

    public WalletApi(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/topup")
    @ApiOperation(value = "Topup User ", response = ResponseEntity.class)
    public ResponseEntity<TopupResponseDto> topupCustomerWallet(@Validated @RequestBody TopupRequestDto topupRequestDto){
        TopupResponseDto topupResponseDto = walletService.topupCustomerWallet(topupRequestDto);
        return ResponseUtil.wrapOrNotFound(topupResponseDto);
    }
}
