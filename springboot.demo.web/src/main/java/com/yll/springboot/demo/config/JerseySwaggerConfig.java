package com.yll.springboot.demo.config;
import javax.annotation.PostConstruct;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.AcceptHeaderApiListingResource;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
public class JerseySwaggerConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path}")
    private String apiPath;
    
    @Value("${server.port:8080}")
    private String port;
    
    public JerseySwaggerConfig() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        this.registerEndpoints();
    }

    @PostConstruct
    public void init() {
        this.register(ApiListingResource.class);
        this.register(AcceptHeaderApiListingResource.class);
        this.register(SwaggerSerializers.class);
        // Register components where DI is needed
        this.configureJerseySwagger();
    }

    private void registerEndpoints() {
        this.packages("com.jzg.springboot.demo.service");
        // Access through /<Jersey‘s servlet path>/application.wadl
        this.register(WadlResource.class);
    }

    @Bean
    private BeanConfig configureJerseySwagger() {
        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-example");
        config.setTitle("Spring Boot + Jersey + Swagger Example");
        config.setHost("localhost:"+this.port);
        config.setVersion("v1");
        config.setContact("jzg");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.jzg.springboot.demo.service");
        config.setPrettyPrint(true);
        config.setScan(true);
        return config;
    }
    
}