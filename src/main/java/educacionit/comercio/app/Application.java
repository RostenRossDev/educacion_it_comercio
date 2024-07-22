package educacionit.comercio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application  implements CommandLineRunner {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password="123";

		for(int i=0;i<2;i++) {
			String bcrypPass=passwordEncoder.encode(password);
			System.out.println(bcrypPass);
		}
	}
}
