package by.dzikovskiy.idt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.properties")
@Getter
@Setter
public class ApiProperties {
    private String stackExchangeApiLink = "https://api.stackexchange.com/2.2/answers?site=stackoverflow&page=1&pagesize=10&order=desc&sort=activity&filter=default";
}
