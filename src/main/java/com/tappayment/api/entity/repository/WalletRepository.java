package com.tappayment.api.entity.repository;

import com.tappayment.api.entity.WalletEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends MongoRepository<WalletEntity,Integer> {
}
