package org.springexample.config;

import org.springexample.repository.OwnerRepository;
import org.springexample.repository.imp.OwnerRepositoryImp;
import org.springexample.service.OwnerService;
import org.springexample.service.mock.OwnerServiceImp;
import org.springexample.service.imp.OwnerServiceImp1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public OwnerService ownerService() {
        return new OwnerServiceImp(10);
    }
    @Bean("ownerService")
    public OwnerService ownerService2() {
        return new OwnerServiceImp1(ownerRepository(), 10);
    }
    public OwnerRepository ownerRepository() {
        return new OwnerRepositoryImp();
    }
}
