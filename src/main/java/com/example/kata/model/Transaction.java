package com.example.kata.model;

import com.example.kata.dto.TransactionType;

import java.time.LocalDateTime;

/**
 * <p>Classe model représentant la transaction.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
public record Transaction(LocalDateTime date, TransactionType type, double amount, double balance) {
}
