package com.example.kata.controller;

import com.example.kata.dto.TransactionCommand;
import com.example.kata.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Classe représentant les services API du compte bancaire.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
@RestController
@RequestMapping("/api/account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    public void deposit(@RequestBody TransactionCommand command) {
        bankAccountService.deposit(command);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@RequestBody TransactionCommand command) {
        bankAccountService.withdraw(command);
    }
}
