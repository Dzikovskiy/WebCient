package by.dzikovskiy.idt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class ApplicationConfiguration {
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

}
