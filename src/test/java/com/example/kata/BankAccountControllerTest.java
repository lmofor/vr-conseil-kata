package com.example.kata;

import com.example.kata.controller.BankAccountController;
import com.example.kata.dto.TransactionCommand;
import com.example.kata.dto.TransactionType;
import com.example.kata.model.Account;
import com.example.kata.model.Transaction;
import com.example.kata.repository.BankAccountRepository;
import com.example.kata.service.BankAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>Classe de test du controller.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
@WebMvcTest(BankAccountController.class)
public class BankAccountControllerTest {

    private static final String API = "/api/account";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BankAccountService bankAccountService;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Test de depot sur un compte bancaire !")
    void deposit() throws Exception {

        //GIVEN
        TransactionCommand command = new TransactionCommand(100);

        //WHEN
        this.bankAccountService.deposit(command);

        //THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post(API + "/deposit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test de retrait sur un compte bancaire !")
    void withdraw() throws Exception {

        //GIVEN
        TransactionCommand command = new TransactionCommand(100);

        //WHEN
        this.bankAccountService.withdraw(command);

        //THEN
        mockMvc.perform(MockMvcRequestBuilders.post(API + "/withdraw")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test de relevé du compte bancaire !")
    void statement() throws Exception {

        //GIVEN
        Transaction transaction = new Transaction(LocalDate.now(), TransactionType.DEPOSIT, 100, 100);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        //WHEN
        when(bankAccountService.transactions()).thenReturn(transactions);

        //THEN
        mockMvc.perform(MockMvcRequestBuilders.get(API + "/statement")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amount").value(100))
                .andExpect(jsonPath("$[0].type").value(TransactionType.DEPOSIT.toString()))
                .andExpect(jsonPath("$[0].balance").value(100));
    }
}
