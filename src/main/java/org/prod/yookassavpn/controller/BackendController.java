package org.prod.yookassavpn.controller;

import lombok.RequiredArgsConstructor;
import org.prod.yookassavpn.dto.PaymentDTO;
import org.prod.yookassavpn.models.PaymentResponse;
import org.prod.yookassavpn.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/yookassa")
@RequiredArgsConstructor
public class BackendController {

    private final PaymentService paymentService;

    @PostMapping("/createPayment")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) {
        if (paymentService.validateDTO(paymentDTO)) {
            PaymentResponse payment = paymentService.getPayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        } else {
            Map<String, String> errorResponse = Map.of("error", "Invalid payment data");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }



}
