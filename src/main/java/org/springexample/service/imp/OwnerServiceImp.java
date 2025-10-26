package org.springexample.service.imp;

import org.springexample.repository.OwnerRepository;
import org.springexample.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImp implements OwnerService {
    private OwnerRepository ownerRepository;
    private int ownerId;

    public OwnerServiceImp() {
        System.out.println("OwnerServiceImpl bean created.");
    }

    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String findOwner() {
        return ownerRepository.findOwner(ownerId);
    }
}
