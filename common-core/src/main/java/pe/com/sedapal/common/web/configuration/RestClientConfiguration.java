package pe.com.sedapal.common.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/pe/com/sedmail/cliente/ws/config/applicationContext.xml")
public class RestClientConfiguration {

}
