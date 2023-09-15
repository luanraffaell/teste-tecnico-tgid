package com.tgid.config;

import com.tgid.services.NotificacaoService;
import com.tgid.services.factory.EmailNotificacaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public NotificacaoService notificacaoService(){
        return new EmailNotificacaoService();
    }
}
