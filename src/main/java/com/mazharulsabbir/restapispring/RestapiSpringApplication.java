package com.mazharulsabbir.restapispring;

import com.mazharulsabbir.restapispring.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories("com.mazharulsabbir.restapispring.data.repository")
@EntityScan("com.mazharulsabbir.restapispring.data.model.user")
public class RestapiSpringApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
//        JsonReader.setLenient(true);
        SpringApplication.run(RestapiSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Datasource: "+dataSource);

    }
}
