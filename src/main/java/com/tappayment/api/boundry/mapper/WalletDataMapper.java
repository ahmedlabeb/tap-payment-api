package com.tappayment.api.boundry.mapper;

import com.tappayment.api.boundry.helper.TopupRequestDto;
import com.tappayment.api.boundry.helper.TopupResponseDto;
import com.tappayment.api.entity.WalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WalletDataMapper {
    @Mappings({
            @Mapping(target = "amount", source = "topupAmount"),
            @Mapping(target = "currency", source = "topupCurrency"),
            @Mapping(target = "customer",source = "customerDto"),
            @Mapping(target = "fees",source = "feesDto")
    })
    WalletEntity toEntites(TopupRequestDto topupRequestDto);

    @Mappings({
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "currency", source = "currency"),
            @Mapping(target = "customerDto",source = "customer"),
            @Mapping(target = "feesDto",source = "fees"),
            @Mapping(target = "creationDate",expression = "java(walletEntity.getCreatedAt().toEpochMilli())")
    })
    TopupResponseDto toDto(WalletEntity walletEntity);
}
