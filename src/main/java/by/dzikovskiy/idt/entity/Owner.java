package by.dzikovskiy.idt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Owner {
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("display_name")
    private String displayName;
    private String link;

}
