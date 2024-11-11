package com.engineering_bureau.corporate_service.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@OpenAPIDefinition(
        info = @Info(
                title = "Corporate service application api",
                version = "1.0"),
        servers = @Server(
                description = "localhost",
                url = "http://localhost:8080/")
)
public class SwaggerConfiguration {
}
