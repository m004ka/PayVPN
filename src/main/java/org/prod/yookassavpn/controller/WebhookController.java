package org.prod.yookassavpn.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.prod.yookassavpn.dto.WebhookNotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/webhook")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    private static final List<String> YOOKASSA_IPS = List.of(
            "185.71.76.0/27",
            "185.71.77.0/27",
            "77.75.153.0/25",
            "77.75.156.11",
            "77.75.156.35",
            "77.75.154.128/25",
            "2a02:5180::/32"
    );

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody WebhookNotificationDTO notification,
                                                HttpServletRequest request) {
        String remoteIp = request.getRemoteAddr();
        
        if (!isValidIp(remoteIp)) {
            logger.warn("Попытка подделки webhook от IP: {}", remoteIp);
            return ResponseEntity.status(403).body("Forbidden");
        }

        logger.info("Получено уведомление от ЮKassa: {}", notification);
        
        switch (notification.getEvent()) {
            case "payment.succeeded":
                logger.info("✅ Платеж успешно завершен: ID = {}", notification.getObject().getId());
                break;
            case "payment.waiting_for_capture":
                logger.info("⏳ Платеж ожидает подтверждения: ID = {}", notification.getObject().getId());
                break;
            case "payment.canceled":
                logger.info("❌ Платеж отменен: ID = {}", notification.getObject().getId());
                break;
            default:
                logger.warn("⚠️ Неизвестное событие: {}", notification.getEvent());
        }
        
        return ResponseEntity.ok("OK");
    }
    
    private boolean isValidIp(String remoteIp) {
        return YOOKASSA_IPS.contains(remoteIp);
    }
}
