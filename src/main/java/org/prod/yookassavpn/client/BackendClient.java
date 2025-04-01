package org.prod.yookassavpn.client;


import lombok.RequiredArgsConstructor;
import org.prod.yookassavpn.dto.NotificationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class BackendClient {

    private final BackendFeignClient backendFeignClient;

    public HttpStatus sendNotification(NotificationDTO notificationDTO) {
        return backendFeignClient.notification(notificationDTO);
    }

}
