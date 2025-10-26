package org.springexample.config;

import org.springexample.repository.OwnerRepository;
import org.springexample.repository.imp.OwnerRepositoryImp;
import org.springexample.service.OwnerService;
import org.springexample.service.imp.OwnerServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "org.springexample")
@Configuration
public class AppConfig {
}
