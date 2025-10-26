package org.springexample.service.imp;

import org.springexample.repository.OwnerRepository;
import org.springexample.service.OwnerService;

public class OwnerServiceImp1 implements OwnerService {
    private OwnerRepository ownerRepository;
    private int ownerId;

    public OwnerServiceImp1(OwnerRepository ownerRepository, int ownerId) {
        this.ownerRepository = ownerRepository;
        this.ownerId = ownerId;
        System.out.println("OwnerServiceImpl bean created.");
    }

    @Override
    public String findOwner() {
        return ownerRepository.findOwner(ownerId);
    }
}
