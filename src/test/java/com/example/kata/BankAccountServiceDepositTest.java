package com.example.kata;

import com.example.kata.dto.TransactionCommand;
import com.example.kata.exception.TransactionException;
import com.example.kata.model.Account;
import com.example.kata.repository.BankAccountRepository;
import com.example.kata.service.BankAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>Classe de test unitaire pour les opérations de dépot d'un compte bancaire.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
public class BankAccountServiceDepositTest {

    private BankAccountService bankAccountService;
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp() {
        this.bankAccountRepository = new BankAccountRepository();
        this.bankAccountService = new BankAccountService(this.bankAccountRepository);
    }

    @Test
    @DisplayName("Test de dépot avec une commande vide !")
    void testDepositWithNullCommand() {
        //Given
        TransactionCommand command = null;

        //When
        TransactionException thrown = Assertions
                .assertThrows(TransactionException.class, () -> bankAccountService.deposit(command));

        //Then
        assertEquals("Please enter the amount !", thrown.getMessage());
    }

    @Test
    @DisplayName("Test de dépot avec le montant 0 !")
    void testDepositWithZeroValue() {
        //Given
        TransactionCommand command = new TransactionCommand(0);

        //When
        TransactionException thrown = Assertions
                .assertThrows(TransactionException.class, () -> bankAccountService.deposit(command));

        //Then
        assertEquals("Amount must be positive !", thrown.getMessage());
    }

    @Test
    @DisplayName("Test de dépot avec un bon montant !")
    void testDepositWithGoodValue() {
        //Given
        TransactionCommand command = new TransactionCommand(100);

        //When
        bankAccountService.deposit(command);

        //Then
        Account account = this.bankAccountRepository.getAccount();
        assertEquals(100, account.getBalance());
    }
}
