package api.microservices.itemsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ItemsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemsServiceApplication.class, args);
	}

}
