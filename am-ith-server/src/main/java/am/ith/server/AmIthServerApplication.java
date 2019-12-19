package am.ith.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"am.ith.server", "am.ith.service"})
@EntityScan("am.ith")
@EnableJpaRepositories("am.ith")
public class AmIthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AmIthServerApplication.class, args);
  }
}
