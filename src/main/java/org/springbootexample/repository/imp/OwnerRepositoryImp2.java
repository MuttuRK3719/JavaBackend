package org.springbootexample.repository.imp;

import org.springbootexample.exception.OwnerNotFoundException;
import org.springbootexample.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("local")
@Repository
public class OwnerRepositoryImp2 implements OwnerRepository {
    @Value("${owner.found}")
    private String ownerFound;
    public OwnerRepositoryImp2() {
        System.out.println("OwnerRepositoryImpl mock bean created.");
    }
    @Override
    public String findOwner(int ownerId) throws OwnerNotFoundException {
        return String.format(ownerFound, ownerId);
    }
}
