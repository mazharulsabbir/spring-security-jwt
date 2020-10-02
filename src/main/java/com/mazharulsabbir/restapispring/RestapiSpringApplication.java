package com.mazharulsabbir.restapispring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiSpringApplication {

    public static void main(String[] args) {
//        JsonReader.setLenient(true);
        SpringApplication.run(RestapiSpringApplication.class, args);
    }
}
