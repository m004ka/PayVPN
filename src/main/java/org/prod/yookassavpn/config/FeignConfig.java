package org.prod.yookassavpn.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class FeignConfig {

    @Bean
    public HttpMessageConverters httpMessageConverters() {
        return new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
    }

    @Bean
    public SpringDecoder feignDecoder() {
        return new SpringDecoder(() -> httpMessageConverters());
    }

    @Bean
    public SpringEncoder feignEncoder() {
        return new SpringEncoder(() -> httpMessageConverters());
    }
}
