package com.tappayment.api.boundry;

import com.tappayment.api.boundry.helper.TopupRequestDto;
import com.tappayment.api.boundry.helper.TopupResponseDto;
import com.tappayment.api.control.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "Wallet Enpoint")
public class WalletApi {

    private WalletService walletService;

    public WalletApi(WalletService walletService) {
        this.walletService = walletService;
    }

    @RequestMapping(value = "/topup", method = RequestMethod.POST)
    @ApiOperation(value = "Topup User ", response = ResponseEntity.class)
    public ResponseEntity<TopupResponseDto> topupCustomerWallet(@Validated @RequestBody TopupRequestDto topupRequestDto){
        TopupResponseDto topupResponseDto = walletService.topupCustomerWallet(topupRequestDto);
        return ResponseUtil.wrapOrNotFound(topupResponseDto);
    }
}
