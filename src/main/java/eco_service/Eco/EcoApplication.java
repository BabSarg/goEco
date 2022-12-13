package eco_service.Eco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EcoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoApplication.class, args);
	}

}
