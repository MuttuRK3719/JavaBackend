package org.springbootexample.service.imp;

import org.springbootexample.exception.OwnerNotFoundException;
import org.springbootexample.repository.OwnerRepository;
import org.springbootexample.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImp implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Value("${owner.id}")
    private int ownerId;

    public OwnerServiceImp() {
        System.out.println("OwnerServiceImpl bean created.");
    }

    @Override
    public String findOwner() throws OwnerNotFoundException {
        return ownerRepository.findOwner(ownerId);
    }

    @Override
    public void modifyOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
