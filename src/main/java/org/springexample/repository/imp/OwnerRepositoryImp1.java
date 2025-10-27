package org.springexample.repository.imp;

import org.springexample.repository.OwnerRepository;
import org.springframework.stereotype.Repository;

@Repository("specialRepository")
public class OwnerRepositoryImp1 implements OwnerRepository {
    public OwnerRepositoryImp1() {
        System.out.println("OwnerRepositoryImpl special bean created.");
    }
    @Override
    public String findOwner(int ownerId) {
        return String.format("Found owner with ownerId %s from special repository", ownerId);
    }
}
