package dev.lokeshbisht.account_payment_service.exceptions.handler;

import dev.lokeshbisht.account_payment_service.dto.error.ErrorResponseDto;
import dev.lokeshbisht.account_payment_service.enums.ErrorCode;
import dev.lokeshbisht.account_payment_service.exceptions.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleInternalServerErrorException(InternalServerErrorException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.ACC_PYM_ERR_INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
