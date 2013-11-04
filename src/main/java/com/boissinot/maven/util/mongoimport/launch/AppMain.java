package com.boissinot.maven.util.mongoimport.launch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Gregory Boissinot
 */
public class AppMain {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        final ImportService importService = applicationContext.getBean(ImportService.class);
        importService.importArtifacts();
    }

}
