package com.fiap.tc;

import com.fiap.tc.infrastructure.core.security.property.OriginApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.awt.image.BufferedImage;


@SpringBootApplication
@EnableConfigurationProperties(OriginApiProperty.class)
public class TcAuthBackendApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(TcAuthBackendApiApplication.class, args);
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
