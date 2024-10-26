package com.example.kata.repository;

import com.example.kata.model.Account;
import org.springframework.stereotype.Repository;

/**
 * <p>La classe de persistance des données de compte bancaire.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
@Repository
public class BankAccountRepository {

    private final Account account = new Account();

    public Account getAccount() {
        return account;
    }
}
