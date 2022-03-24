package com.github.switchover.failure.group.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configurable
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Failure Notification Service Api Documentation")
            .description("This is REST API Documentation of the Failure Notification Service.")
            .version("0.1")
            .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("v*/**"))
            .build()
            .apiInfo(apiInfo());
    }
}
