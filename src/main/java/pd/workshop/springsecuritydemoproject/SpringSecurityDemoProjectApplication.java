package pd.workshop.springsecuritydemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("pd.workshop.springsecuritydemoproject")
@SpringBootApplication
public class SpringSecurityDemoProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringSecurityDemoProjectApplication.class, args);
	}

}

// Learning -->
//Setup
// Basic Auth Setup
