package org.prod.yookassavpn.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class PaymentNotification {
    private String type;
    private String event;
    private PaymentObject object;

    @Data
    public static class PaymentObject {
        private String id;
        private String status;
        private boolean paid;
        private Amount amount;
        private AuthorizationDetails authorizationDetails;
        private Instant createdAt;
        private String description;
        private Instant expiresAt;
        private Map<String, String> metadata;
        private PaymentMethod paymentMethod;
        private boolean refundable;
        private boolean test;
    }

    @Data
    public static class Amount {
        private String value;
        private String currency;
    }

    @Data
    public static class AuthorizationDetails {
        private String rrn;
        private String authCode;
        @JsonProperty("three_d_secure")
        private ThreeDSecure threeDSecure;
    }

    @Data
    public static class ThreeDSecure {
        private boolean applied;
    }

    @Data
    public static class PaymentMethod {
        private String type;
        private String id;
        private boolean saved;
        private Card card;
        private String title;
    }

    @Data
    public static class Card {
        private String first6;
        private String last4;
        private String expiryMonth;
        private String expiryYear;
        private String cardType;
        private String issuerCountry;
        private String issuerName;
    }
}
