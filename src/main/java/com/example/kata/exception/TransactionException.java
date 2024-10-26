package com.example.kata.exception;

/**
 * <p>Classe d'exception pour la transaction.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
public class TransactionException extends RuntimeException {

    public TransactionException(String message) {
        super(message);
    }
}
