package com.boissinot.maven.util.mongoimport.launch;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Gregory Boissinot
 */
public class AppMain {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("remote");
        applicationContext.register(JavaConfig.class);
        applicationContext.refresh();
        final ImportService importService = applicationContext.getBean(ImportService.class);
        importService.importArtifacts();
    }

}
