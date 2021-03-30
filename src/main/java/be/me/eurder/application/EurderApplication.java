package be.me.eurder.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "be.me.eurder")
public class EurderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurderApplication.class);
    }

}
