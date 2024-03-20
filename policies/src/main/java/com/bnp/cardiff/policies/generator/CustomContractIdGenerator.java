package com.bnp.cardiff.policies.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class CustomContractIdGenerator implements IdentifierGenerator {
    private static AtomicLong counter = new AtomicLong(100000L); // Valeur initiale

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Génération de l'ID du contrat au format "GT" suivi d'une suite de chiffres
        String contractId = "GT" + String.format("%08d", counter.getAndIncrement());
        return contractId;
    }
}
