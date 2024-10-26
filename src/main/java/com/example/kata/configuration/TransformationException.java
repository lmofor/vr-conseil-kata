package com.example.kata.configuration;

import com.example.kata.exception.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Classe de gestion des exceptions.</p>
 *
 * @author mamadou.diarra 2024-10-26
 */
@RestControllerAdvice
public class TransformationException {


    @ExceptionHandler(TransactionException.class)
    public ProblemDetail handleTransactionException(TransactionException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
