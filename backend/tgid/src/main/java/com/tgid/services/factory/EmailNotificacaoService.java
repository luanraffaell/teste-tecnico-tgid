package com.tgid.services.factory;

import com.tgid.services.NotificacaoService;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificacaoService implements NotificacaoService {
    @Override
    public void enviarNotificacao(String email, String mensagem) {
        System.out.println("Email:"+email);
        System.out.println("Mensagem:"+mensagem);
    }
}
