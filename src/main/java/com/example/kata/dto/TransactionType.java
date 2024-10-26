package com.example.kata.dto;

/**
 * <p>Classe énumération représentant les types de transaction.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
public enum TransactionType {
    DEPOSIT, WITHDRAWAL;

    public boolean isDeposit() {
        return DEPOSIT.equals(this);
    }
}
