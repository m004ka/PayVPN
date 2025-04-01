package org.prod.yookassavpn.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDTO {
    private String order_id;

    private String status;
}
