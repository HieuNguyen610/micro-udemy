package hieu.microudemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroUdemyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroUdemyApplication.class, args);
    }

}
