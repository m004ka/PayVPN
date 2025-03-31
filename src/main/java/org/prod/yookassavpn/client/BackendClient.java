package org.prod.yookassavpn.client;


import lombok.RequiredArgsConstructor;
import org.prod.yookassavpn.dto.NotificationDTO;
import org.prod.yookassavpn.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class BackendClient {

    private final BackendFeignClient backendFeignClient;

    public HttpStatus sendNotification(NotificationDTO notificationDTO) {
        return backendFeignClient.notification(notificationDTO);
    }

}
