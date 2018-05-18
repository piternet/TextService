package rtbhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static TextService textService;

    @Bean
    public TextService textService() {
        return textService;
    }

    public static void main(String[] args) {
        textService = new TextService(InputReader.getPath());
        SpringApplication.run(Application.class, args);
    }
}