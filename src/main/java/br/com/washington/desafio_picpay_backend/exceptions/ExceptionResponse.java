package br.com.washington.desafio_picpay_backend.exceptions;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

public record ExceptionResponse (Date timestamp, String message, String details){}
