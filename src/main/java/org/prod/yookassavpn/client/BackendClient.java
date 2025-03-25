package org.prod.yookassavpn.client;

import org.prod.yookassavpn.dto.WebhookNotificationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class BackendClient {
    @Value("${backend.host}")
    private String baseUrl;
    private final WebClient webClient = WebClient.create(baseUrl);

    public Mono<?> paymentNotification(WebhookNotificationDTO webhookNotification){
        return webClient.post()
                .uri("/notification")
                .bodyValue(webhookNotification)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorMessage -> Mono.error(new RuntimeException(errorMessage)))
                )
                .bodyToMono(WebhookNotificationDTO.class)
                .onErrorResume(e -> {

                    return Mono.just((WebhookNotificationDTO) Map.of("error", "Payment creation failed: " + e.getMessage()));
                });
    }
}
