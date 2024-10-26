package com.example.kata.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Classe model représentant le compte bancaire.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
public class Account {

    private double balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
