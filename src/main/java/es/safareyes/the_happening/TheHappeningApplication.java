package es.safareyes.the_happening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TheHappeningApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheHappeningApplication.class, args);
    }

}