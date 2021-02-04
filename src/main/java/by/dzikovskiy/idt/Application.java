package by.dzikovskiy.idt;

import by.dzikovskiy.idt.component.StackResponseClient;
import by.dzikovskiy.idt.entity.Owner;
import by.dzikovskiy.idt.entity.StackResponse;
import by.dzikovskiy.idt.service.OwnerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
@ConfigurationPropertiesScan("by.dzikovskiy.idt.properties")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        ApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);

        StackResponseClient stackResponseClient = ctx.getBean(StackResponseClient.class);

        StackResponse stackResponse = stackResponseClient.getStackResponse().block();

        System.out.println(stackResponse);

        OwnerService ownerService = ctx.getBean(OwnerService.class);

        List<Owner> owners = ownerService.getOwnersFromStackResponse(stackResponse);
        owners.forEach(System.out::println);
    }

}
