package org.prod.yookassavpn.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Amount {

    private final double value;

    private final String currency = "RUB";
}

