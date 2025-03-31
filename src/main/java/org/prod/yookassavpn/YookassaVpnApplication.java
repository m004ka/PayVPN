package org.prod.yookassavpn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YookassaVpnApplication {

    public static void main(String[] args) {
        SpringApplication.run(YookassaVpnApplication.class, args);
    }

}
