package br.com.washington.desafio_picpay_backend.exceptions.config;

public class MessageException {

    public static final String USER_NOT_FOUND_FOR_THIS_ID = "User not found for this ID!";
    public static final String NO_USER_RECORD_IN_DATABASE = "No user record in database!";
    public static final String USER_ALREADY_EXISTS_FOR_THIS_DOCUMENT = "User already exists for this document!";
    public static final String USER_ALREADY_EXISTS_FOR_THIS_EMAIL = "User already exists for this email!";
    public static final String INSUFFICIENT_BALANCE = "Insufficient balance!";
    public static final String BUSINESS_NOT_ALLOW_TO_SEND_MONEY = "Sorry, but business users are not allowed to send money!";
    public static final String TRANSACTION_NOT_ALLOWED = "Transaction not allowed!";
    public static final String TRANSACATION_AUTHORIZATION_FORBIDDEN = "Transaction authorization forbidden!";
}
