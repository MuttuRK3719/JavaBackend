package org.springexample.repository.imp;

import org.springexample.repository.OwnerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
@Primary
@Repository("commonRepository")
public class OwnerRepositoryImp implements OwnerRepository {
    public OwnerRepositoryImp() {
        System.out.println("OwnerRepositoryImpl common bean created.");
    }

    @Override
    public String findOwner(int ownerId) {
        return String.format("Found owner with ownerId %s from common repository", ownerId);
    }
}
