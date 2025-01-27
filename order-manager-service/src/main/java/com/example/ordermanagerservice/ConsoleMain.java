package com.example.ordermanagerservice;


import com.example.ordermanagerservice.util.ConsoleMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
class ConsoleMain {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ConsoleMain.class, args);
        ConsoleMenu.startConsole(context);
    }
}
