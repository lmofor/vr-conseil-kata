package com.example.kata;

import com.example.kata.dto.TransactionType;
import com.example.kata.model.Account;
import com.example.kata.model.Transaction;
import com.example.kata.repository.BankAccountRepository;
import com.example.kata.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>Classe de test unitaire pour la récupération du rélévé des opérations d'un compte bancaire.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
public class BankAccountServiceStatementTest {

    private BankAccountService bankAccountService;
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp() {
        this.bankAccountRepository = new BankAccountRepository();
        this.bankAccountService = new BankAccountService(this.bankAccountRepository);
    }

    @Test
    @DisplayName("Test de recupération de relevé vide !")
    void testToGetVoidStatement() {
        //Given

        //When
        List<Transaction> transactions = bankAccountService.transactions();

        //Then
        assertEquals(0, transactions.size());
    }

    @Test
    @DisplayName("Test de recupération de relevé !")
    void testToGetStatement() {
        //Given
        Account account = this.bankAccountRepository.getAccount();
        Transaction transaction = new Transaction(LocalDate.now(), TransactionType.DEPOSIT, 1000, 1000);
        account.getTransactions().add(transaction);

        //When
        List<Transaction> transactions = bankAccountService.transactions();

        //Then
        assertEquals(1, transactions.size());
    }
}
