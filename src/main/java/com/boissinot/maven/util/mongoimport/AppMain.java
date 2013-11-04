package com.boissinot.maven.util.mongoimport;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Gregory Boissinot
 */
public class AppMain {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/app-config.xml");
        final ImportService importService = applicationContext.getBean(ImportService.class);
        importService.importArtifacts();
    }
}
