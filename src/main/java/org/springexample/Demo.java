package org.springexample;

import org.springexample.config.AppConfig;
import org.springexample.service.OwnerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OwnerService ownerService = context.getBean("ownerService", OwnerService.class);
        System.out.println(ownerService.findOwner());
    }
}
