package com.example.kata.service;

import com.example.kata.dto.TransactionCommand;
import com.example.kata.dto.TransactionType;
import com.example.kata.model.Account;
import com.example.kata.model.Transaction;
import com.example.kata.repository.BankAccountRepository;
import com.example.kata.verification.BankAccountVerification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>Classe service contenant la logique metier des opérations sur le compte bancaire</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountVerification bankAccountVerification;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountVerification = new BankAccountVerification();
    }

    public void deposit(TransactionCommand command) {
        Account account = this.bankAccountRepository.getAccount();
        TransactionType type = TransactionType.DEPOSIT;

        this.bankAccountVerification.checkDepositAmount(command);

        this.saveAccount(account, command, type);
    }

    public void withdraw(TransactionCommand command) {
        Account account = this.bankAccountRepository.getAccount();
        TransactionType type = TransactionType.WITHDRAWAL;

        this.bankAccountVerification.checkDepositAmount(command);
        this.bankAccountVerification.checkAccountBalanceIsSufficient(account, command);

        this.saveAccount(account, command, type);
    }

    private void saveAccount(Account account, TransactionCommand command, TransactionType type) {
        double amount = command.amount();
        double balance = account.getBalance();
        double newBalance = this.calculateNewAccountBalance(balance, amount, type);
        Transaction transaction = this.generateTransaction(type, amount, newBalance);

        account.setBalance(newBalance);
        account.getTransactions().add(transaction);
    }

    private double calculateNewAccountBalance(double balance, double amount, TransactionType type) {
        balance += type.isDeposit() ? amount : -amount;
        return balance;
    }

    private Transaction generateTransaction(TransactionType type, double amount, double balance) {
        return new Transaction(LocalDate.now(), type, amount, balance);
    }
}
