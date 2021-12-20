package com.tappayment.api.entity.listners;

import com.tappayment.api.entity.WalletEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class GenerateUUIDListener extends AbstractMongoEventListener<WalletEntity> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<WalletEntity> event) {
        WalletEntity entity = event.getSource();
        if (entity.isNew()) {
            entity.setTransactionId(UUID.randomUUID());
        }
    }

}
