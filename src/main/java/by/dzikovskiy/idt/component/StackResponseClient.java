package by.dzikovskiy.idt.component;

import by.dzikovskiy.idt.entity.StackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StackResponseClient {

    private final WebClient webClient;


    @Autowired
    public StackResponseClient(WebClient.Builder builder,
                               @Value("${api.properties.stack-exchange-api-link}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    public Mono<StackResponse> getStackResponse() {
        return this.webClient
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(StackResponse.class);
    }

}
