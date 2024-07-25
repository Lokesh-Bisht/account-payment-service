package dev.lokeshbisht.account_payment_service.exceptions.handler;

import dev.lokeshbisht.account_payment_service.constants.ErrorMessages;
import dev.lokeshbisht.account_payment_service.dto.error.ErrorResponseDto;
import dev.lokeshbisht.account_payment_service.enums.ErrorCode;
import dev.lokeshbisht.account_payment_service.exceptions.InternalServerErrorException;
import dev.lokeshbisht.account_payment_service.exceptions.UnauthorizedAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponseDto> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.ACC_PYM_ERR_UNAUTHORIZED_ACCESS, ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleInternalServerErrorException(InternalServerErrorException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.ACC_PYM_ERR_INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUncaughtException(Exception ex) {
        log.error("Uncaught exception: {}", ex.getMessage(), ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.ACC_PYM_ERR_INTERNAL_SERVER_ERROR, ErrorMessages.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
