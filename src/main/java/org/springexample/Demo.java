package org.springexample;

import org.springexample.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
////@Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
public class Demo implements CommandLineRunner {
    @Qualifier("specialService")
    @Autowired
    OwnerService ownerService;
    @Autowired
    OwnerService ownerService1;
    public static void main(String[] args) {
        SpringApplication.run(Demo.class,args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println(ownerService.findOwner());
        System.out.println(ownerService1.findOwner());

    }
}
