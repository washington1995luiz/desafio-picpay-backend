package br.com.washington.desafio_picpay_backend.models;

import java.util.Map;

public record MessageTransaction (String status, UserVO sender, UserVO receive,  Map<String, String> notification){
}
