package org.prod.yookassavpn.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDTO {

    private Long telegramUserId;

    private String username;

    private String mail;

    private Double value;

    private Boolean receipt;

    private Long orderId;
}
