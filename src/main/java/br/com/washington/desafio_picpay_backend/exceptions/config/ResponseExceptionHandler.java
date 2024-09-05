package br.com.washington.desafio_picpay_backend.exceptions.config;

import br.com.washington.desafio_picpay_backend.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerAllExceptions(Exception ex, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
                        request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerNotFoundException(Exception ex, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(),
                        request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception ex, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse( new Date(), ex.getMessage(),
                        request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<ExceptionResponse> handlerInsufficientBalanceException(Exception ex, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse( new Date(), ex.getMessage(),
                request.getDescription(false)), HttpStatus.FORBIDDEN);
    }

}
