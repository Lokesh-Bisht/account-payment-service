package dev.lokeshbisht.account_payment_service.exceptions;

public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
