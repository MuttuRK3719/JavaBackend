package org.springexample;

import org.springexample.service.OwnerService;
import org.springexample.service.imp.OwnerServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.springexample")
public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Demo.class);
        OwnerService ownerService = context.getBean("specialService", OwnerService.class);
        OwnerService ownerService1=context.getBean("commonService", OwnerService.class);
        System.out.println(ownerService.findOwner());
        System.out.println(ownerService1.findOwner());
    }
}
