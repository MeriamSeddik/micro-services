package com.bnp.cardiff.claimrequest.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
public class OpenApiConfiguration
{
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8100");
        server.setDescription("Claim request server");
        Contact myContact = new Contact();
        myContact.setName("Mariem Seddik");
        myContact.setEmail("meriam.seddik2@esprit.tn\n");

        Info information = new Info()
                .title("Claim Request API")
                .version("1.0")
                .description("This API exposes endpoints to manage claim request.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
