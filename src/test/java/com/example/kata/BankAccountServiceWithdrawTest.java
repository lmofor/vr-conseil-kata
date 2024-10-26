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
 * <p>Classe de test unitaire pour les opérations de retrait d'un compte bancaire.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
public class BankAccountServiceWithdrawTest {

    private BankAccountService bankAccountService;
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp() {
        this.bankAccountRepository = new BankAccountRepository();
        this.bankAccountService = new BankAccountService(this.bankAccountRepository);
    }

    @Test
    @DisplayName("Test de retrait avec une commande vide !")
    void testWithdrawWithNullCommand() {
        //Given
        TransactionCommand command = null;

        //When
        TransactionException thrown = Assertions
                .assertThrows(TransactionException.class, () -> bankAccountService.withdraw(command));

        //Then
        assertEquals("Please enter the amount !", thrown.getMessage());
    }

    @Test
    @DisplayName("Test de retrait avec le montant 0 !")
    void testWithdrawWithZeroValue() {
        //Given
        TransactionCommand command = new TransactionCommand(0);

        //When
        TransactionException thrown = Assertions
                .assertThrows(TransactionException.class, () -> bankAccountService.withdraw(command));

        //Then
        assertEquals("Amount must be positive !", thrown.getMessage());
    }

    @Test
    @DisplayName("Test de retrait avec un montant inferieur !")
    void testWithdrawWithInsufficientValue() {
        //Given
        TransactionCommand command = new TransactionCommand(100);

        //When
        TransactionException thrown = Assertions
                .assertThrows(TransactionException.class, () -> bankAccountService.withdraw(command));

        //Then
        assertEquals("The account balance is insufficient !", thrown.getMessage());
    }

    @Test
    @DisplayName("Test de retrait avec un bon montant !")
    void testWithdrawWithGoodValue() {
        //Given
        Account account = this.bankAccountRepository.getAccount();
        account.setBalance(150);

        TransactionCommand command = new TransactionCommand(100);

        //When
        bankAccountService.withdraw(command);

        //Then
        assertEquals(50, account.getBalance());
    }
}
