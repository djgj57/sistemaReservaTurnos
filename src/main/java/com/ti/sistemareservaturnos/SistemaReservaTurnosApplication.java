package com.ti.sistemareservaturnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SistemaReservaTurnosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaReservaTurnosApplication.class, args);
    }

}
