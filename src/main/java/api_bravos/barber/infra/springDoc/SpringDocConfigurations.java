package api_bravos.barber.infra.springDoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components().addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Bravos API")
                        .description("API Rest da aplicação Bravos Barbearia, contendo as funcionalidades de CRUD de barbeiros e de usuarios, além de agendamento e cancelamento de horários.")
                        .contact(new Contact()
                                .name("Toyo Softwares House")
                                .email("backend@bravos.med"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://bravos.barber/api/licenca")));
    }

}
