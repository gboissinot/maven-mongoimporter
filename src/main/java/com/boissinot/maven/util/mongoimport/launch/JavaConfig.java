package com.boissinot.maven.util.mongoimport.launch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * @author Gregory Boissinot
 */
@Configuration
@ImportResource({
        "META-INF/spring/infra-config.xml",
        "META-INF/spring/app-config.xml",
        "META-INF/spring/app-flow.xml"
})
public class JavaConfig {

    static
    @Bean
    public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[]{new ClassPathResource("infra.properties")};
        placeholderConfigurer.setLocations(resourceLocations);
        return placeholderConfigurer;
    }
}
