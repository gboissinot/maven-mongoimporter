package com.boissinot.maven.util.mongoimport.launch;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


/**
 * @author Gregory Boissinot
 */
@Configuration
@ImportResource({
        "META-INF/spring/infra-config.xml",
        "META-INF/spring/app-config.xml",
        "META-INF/spring/app-couchbase-flow.xml",
        "META-INF/spring/app-mongodb-flow.xml"
})
public class JavaConfig {
}
