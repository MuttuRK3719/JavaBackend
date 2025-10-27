package org.springbootexample.repository.imp;

import org.springbootexample.exception.OwnerNotFoundException;
import org.springbootexample.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("production")
@Repository
public class OwnerRepositoryImp implements OwnerRepository {
    @Value("${owner.found}")
    private String ownerFound;
    @Value("${owner.not.found}")
    private String ownerNotFound;
    public OwnerRepositoryImp() {
        System.out.println("OwnerRepositoryImpl bean created.");
    }

    @Override
    public String findOwner(int ownerId) throws OwnerNotFoundException {
        if (ownerId % 2 == 0) {
            return String.format(ownerFound, ownerId);
        } else {
            throw new OwnerNotFoundException(String.format(ownerNotFound, ownerId));
        }
    }
}
