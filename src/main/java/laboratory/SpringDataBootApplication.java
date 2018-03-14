package laboratory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringDataBootApplication {



	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SpringDataBootApplication.class).headless(false).run(args);



	}
}
