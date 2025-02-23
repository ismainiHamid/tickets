package ma.pub.ticketmanageservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@OpenAPIDefinition(
        info = @Info(
                title = "Manage tickets APIs",
                version = "1.0.0",
                description = "APIs for manage tickets."
        ),
        servers = {
                @Server(url = "http://localhost:8080/api", description = "Local development server"),
                @Server(url = "http://localhost:8080/api", description = "Production server")
        }
)
public class TicketManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketManageServiceApplication.class, args);
    }

}
