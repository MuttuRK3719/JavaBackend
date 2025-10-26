package org.springexample.repository.imp;

import org.springexample.repository.OwnerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerRepositoryImp implements OwnerRepository {
    public OwnerRepositoryImp() {
        System.out.println("OwnerRepositoryImpl bean created.");
    }

    @Override
    public String findOwner(int ownerId) {
        return String.format("Found owner from repository with ownerId %s", ownerId);
    }
}
