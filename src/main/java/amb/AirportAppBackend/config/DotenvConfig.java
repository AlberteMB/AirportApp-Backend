package amb.AirportAppBackend.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
public class DotenvConfig {
    @Bean
    public Dotenv dotenv(){
        return Dotenv.load();

    }

 }
