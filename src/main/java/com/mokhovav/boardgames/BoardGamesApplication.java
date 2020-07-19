package com.mokhovav.boardgames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mokhovav.boardgames", "com.mokhovav.base"})
public class BoardGamesApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardGamesApplication.class, args);
    }
}
