package pl.itpoznanski.mvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwagerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**")) //jakie endpointy ma obslugiwac swager, wrzucic do dokumentacji
                .apis(RequestHandlerSelectors.basePackage("pl.itpoznanski.mvc"))
                .build().apiInfo(createApiInfo());
    }
    private ApiInfo createApiInfo() {
        return new ApiInfo("Users API",
                "Users database",
                "1.00",
                "http://itpoznanski.pl",
                new Contact("Grzegorz", "http://itpoznanski.pl", "grzegorz@itpoznanski.pl"),
                "my own licence",
                "http://itpoznanski.pl",
                Collections.emptyList()
        );
    }
}
