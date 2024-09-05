package br.com.washington.desafio_picpay_backend.services.external;

import br.com.washington.desafio_picpay_backend.exceptions.ForbiddenException;
import br.com.washington.desafio_picpay_backend.exceptions.config.MessageException;
import br.com.washington.desafio_picpay_backend.models.UserVO;
import br.com.washington.desafio_picpay_backend.models.external.ExternalAuthorizationModel;
import br.com.washington.desafio_picpay_backend.models.external.MessageNotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalVerification {

    @Autowired
    RestTemplate template;


    public void authenticatedSever(){
        String url = "https://util.devi.tools/api/v2/authorize";
        try{
            ExternalAuthorizationModel response = template.getForObject(url, ExternalAuthorizationModel.class);
            if(response != null && response.status().equalsIgnoreCase("fails") && response.data().get("authorization").toString().equalsIgnoreCase("false")){
                throw new ForbiddenException(MessageException.TRANSACTION_NOT_ALLOWED);
            }
        }catch (Exception e){
            throw new ForbiddenException(MessageException.TRANSACATION_AUTHORIZATION_FORBIDDEN);
        }
    }

    public MessageNotificationModel sendNotification(UserVO user){
            MessageNotificationModel messageNotificationModel = new MessageNotificationModel("Notification sent to user ".concat(user.getFirstName()));
            String url = "https://util.devi.tools/api/v1/notify";
            ResponseEntity<Void> response = template.exchange(url, HttpMethod.POST, HttpEntity.EMPTY , Void.class);
            if(response.getStatusCode().is2xxSuccessful()) return messageNotificationModel;
            throw new ForbiddenException("Service unavailable");
    }
}
