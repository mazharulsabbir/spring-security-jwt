package com.mazharulsabbir.restapispring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RestapiSpringApplication {
    public static void main(String[] args) {
//        JsonReader.setLenient(true);
        SpringApplication.run(RestapiSpringApplication.class, args);
    }
}
