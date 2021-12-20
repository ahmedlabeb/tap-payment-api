package com.tappayment.api.entity.repository;

import com.tappayment.api.entity.WalletEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<WalletEntity,Integer> {
}
