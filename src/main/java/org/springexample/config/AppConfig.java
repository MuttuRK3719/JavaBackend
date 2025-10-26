package org.springexample.config;

import org.springexample.repository.OwnerRepository;
import org.springexample.repository.imp.OwnerRepositoryImp;
import org.springexample.service.OwnerService;
import org.springexample.service.imp.OwnerServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public OwnerService ownerService() {
        OwnerServiceImp ownerService = new OwnerServiceImp();
        ownerService.setOwnerRepository(ownerRepository());
        ownerService.setOwnerId(10);
        return ownerService;
    }
    public OwnerRepository ownerRepository() {
        return new OwnerRepositoryImp();
    }
}
