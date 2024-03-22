package com.bnp.cardiff.claimrequest;

import com.bnp.cardiff.claimrequest.openapi.OpenApiConfiguration;
import io.swagger.v3.oas.models.servers.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
@SpringBootTest
class ClaimRequestApplicationTests {

    @Test
     void testConfigOpenApi(){
        OpenApiConfiguration openApiConfiguration=new OpenApiConfiguration();
        List<Server> serverList= openApiConfiguration.defineOpenApi().getServers();
        for(Server server:serverList){
            Assertions.assertEquals("http://localhost:8100", server.getUrl());
        }


    }
}
