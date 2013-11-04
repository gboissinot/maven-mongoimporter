package com.boissinot.maven.util.mongoimport.launch;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;


/**
 * @author Gregory Boissinot
 */
@Configuration
@ImportResource({
        "META-INF/spring/infra-config.xml",
        "META-INF/spring/app-config.xml",
        "META-INF/spring/app-flow.xml"
})
@PropertySource("infra.properties")
public class JavaConfig {
}
