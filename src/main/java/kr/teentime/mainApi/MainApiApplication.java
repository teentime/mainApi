package kr.teentime.mainApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MainApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApiApplication.class, args);
	}

}
