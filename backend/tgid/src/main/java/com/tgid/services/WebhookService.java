package com.tgid.services;

import com.tgid.domain.transacoes.Transacao;
import com.tgid.services.exceptions.NegocioException;
import com.tgid.services.factory.EmailNotificacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@AllArgsConstructor
@Service
public class WebhookService {
    private final RestTemplate restTemplate;
    private final EmailNotificacaoService emailNotificacaoService;

    public boolean enviarCallback(String emailEmpresa,String emailCliente) {
        try {
            String requestBody = "{ \"message\": \"Transacao realizada}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            String webhookUrl = "https://webhook.site/e06fb9e9-7a7c-4a06-9c5e-244cf736552b";

            ResponseEntity<String> responseEntity = restTemplate.exchange(webhookUrl, HttpMethod.POST, requestEntity, String.class);

            return true;
        } catch (Exception e) {
            emailNotificacaoService.enviarNotificacao(emailEmpresa,
                    "Devido a falha sistemica não foi possivel receber a trancação vinda de:"+emailCliente);
            emailNotificacaoService.enviarNotificacao(emailCliente,
                    "Não foi possivel concluir a transação, tente novamente mais tarde");
            throw new NegocioException("Falha interna, por favor tente mais tarde!");
        }
    }
}