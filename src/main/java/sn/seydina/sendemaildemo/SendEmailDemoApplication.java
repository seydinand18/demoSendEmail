package sn.seydina.sendemaildemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class SendEmailDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SendEmailDemoApplication.class, args);
    }

}
