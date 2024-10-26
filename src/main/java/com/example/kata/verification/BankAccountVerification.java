package com.example.kata.verification;

import com.example.kata.dto.TransactionCommand;
import com.example.kata.exception.TransactionException;
import com.example.kata.model.Account;

import java.util.Objects;

/**
 * <p>Classe de vérification des contraintes sur les opérations d'un compte.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
public class BankAccountVerification {

    private static final int INITIAL_AMOUNT = 0;

    public void checkDepositAmount(TransactionCommand command) {
        if (Objects.isNull(command)) {
            throw new TransactionException("Please enter the amount !");
        }

        if (command.amount() <= INITIAL_AMOUNT) {
            throw new TransactionException("Amount must be positive !");
        }
    }

    public void checkAccountBalanceIsSufficient(Account account, TransactionCommand command) {
        if (account.getBalance() < command.amount()) {
            throw new TransactionException("The account balance is insufficient !");
        }
    }
}
