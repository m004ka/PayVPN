package org.prod.yookassavpn.client;


import org.prod.yookassavpn.config.FeignConfig;
import org.prod.yookassavpn.dto.NotificationDTO;
import org.prod.yookassavpn.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "backendClient", url = "${backend.host}", configuration = FeignConfig.class)
public interface BackendFeignClient {

    @PostMapping()
    HttpStatus notification(@RequestBody NotificationDTO notificationDTO);
}
