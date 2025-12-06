package ru.a2n.sfm.action.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info =
                @Info(
                        title = "Homework project OpenApi",
                        version = "${build.version}",
                        description = "REST API CRUD for Users",
                        license = @License(name = "MIT", url = "https://opensource.org/license/mit"),
                        contact = @Contact(name = "API Support")),
        servers = {
            @Server(url = "http://localhost:8088", description = "Localhost"),
            @Server(url = "http://api.test.net", description = "Test deployment"),
            @Server(url = "http://api.production.com", description = "Production deployment"),
        })
public class OpenApiConfig {}
