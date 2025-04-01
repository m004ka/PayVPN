package org.prod.yookassavpn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.prod.yookassavpn.dto.PaymentDTO;
import org.prod.yookassavpn.models.PaymentRequest;
import org.prod.yookassavpn.models.PaymentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class PaymentService {

    private final RestClient yooKassaClient;

    @SneakyThrows
    public PaymentResponse createPayment(double amount, String description, String userEmail, String orderId) {
        var request = PaymentRequest.create(amount, description, userEmail, orderId);

        String idempotenceKey = UUID.randomUUID().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);
        System.out.println("Отправляемый JSON: " + requestBody);
        System.out.println("Idempotence-Key: " + idempotenceKey);

        return yooKassaClient.post()
                .header("Idempotence-Key", idempotenceKey)
                .body(request)
                .retrieve()
                .body(PaymentResponse.class);
    }


    public PaymentResponse getPayment(PaymentDTO paymentDTO) {
        PaymentResponse url;
        if (paymentDTO.getReceipt()) {
            String description = "Заказ пользователя: " + paymentDTO.getUsername();
            url = createPayment(paymentDTO.getValue(), description, paymentDTO.getMail(), paymentDTO.getOrder_id());
        } else {
            String description = "Заказ пользователя: " + paymentDTO.getUsername();
            url = createPayment(paymentDTO.getValue(), description, "ysupov959@gmail.com", paymentDTO.getOrder_id());
        }

        return url;
    }

    public boolean validateDTO(PaymentDTO paymentDTO) {
        if (paymentDTO.getValue() == null) return false;
        if (paymentDTO.getMail() == null) return false;
        if (paymentDTO.getTelegramUserId() == null) return false;
        if (paymentDTO.getUsername() == null) return false;

        return true;
    }


}
