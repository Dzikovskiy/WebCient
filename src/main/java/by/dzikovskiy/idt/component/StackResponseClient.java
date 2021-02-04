package by.dzikovskiy.idt.component;

import by.dzikovskiy.idt.entity.StackResponse;
import by.dzikovskiy.idt.properties.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StackResponseClient {

    private final ApiProperties apiProperties;

    private final WebClient webClient;

    @Autowired
    public StackResponseClient(ApiProperties apiProperties, WebClient webClient) {
        this.webClient = webClient;
        this.apiProperties = apiProperties;
    }

    public Mono<StackResponse> getStackResponse() {
        return this.webClient
                .get()
                .uri(apiProperties.getStackExchangeApiLink())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(StackResponse.class);
    }

}
