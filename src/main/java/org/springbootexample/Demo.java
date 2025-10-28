package org.springbootexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootexample.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@PropertySource("classpath:message.properties")
@SpringBootApplication(scanBasePackages = "org.springbootexample")
public class Demo implements CommandLineRunner {
    @Autowired
    private OwnerService ownerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String... args) {
        SpringApplication.run(Demo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            LOGGER.info(ownerService.findOwner());
            System.out.println("Enter id of owner:");
            int ownerId = scanner.nextInt();
            ownerService.modifyOwnerId(ownerId);
            LOGGER.info(ownerService.findOwner());
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
    }
}
