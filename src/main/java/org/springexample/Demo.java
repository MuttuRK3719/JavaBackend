package org.springexample;

import org.springexample.config.AppConfig;
import org.springexample.repository.OwnerRepository;
import org.springexample.repository.imp.OwnerRepositoryImp;
import org.springexample.service.OwnerService;
import org.springexample.service.imp.OwnerServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OwnerService ownerService = context.getBean("ownerServiceImp", OwnerService.class);
        OwnerRepository ownerRepository=context.getBean("ownerRepositoryImp",OwnerRepository.class);
        ((OwnerServiceImp)ownerService).setOwnerId(10);
        ((OwnerServiceImp) ownerService).setOwnerRepository(ownerRepository);
        System.out.println(ownerService.findOwner());
    }
}
