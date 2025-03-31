package org.prod.yookassavpn.controller;


import lombok.RequiredArgsConstructor;
import org.prod.yookassavpn.client.BackendClient;
import org.prod.yookassavpn.dto.NotificationDTO;
import org.prod.yookassavpn.models.PaymentNotification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payment/notification")
public class WebhookController {

    private final BackendClient backendClient;

    @PostMapping
    public HttpStatus notification(@RequestBody PaymentNotification paymentNotification) {
        if (paymentNotification.getEvent() == "succeeded") {
         HttpStatus httpStatus =   backendClient.sendNotification(NotificationDTO.builder()
                    .order_id(paymentNotification.getObject().getId())
                    .status(paymentNotification.getObject().getStatus())
                    .build());
            return httpStatus;
        }


        return HttpStatus.BAD_REQUEST;
    }

}
