package br.com.washington.desafio_picpay_backend.models.external;

import java.util.Map;

public record ExternalAuthorizationModel (String status, Map<String, Object> data){
}
