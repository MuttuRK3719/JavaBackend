package org.springexample.service.imp;

import org.springexample.repository.OwnerRepository;
import org.springexample.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImp implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Value("10")
    private int ownerId;

    public OwnerServiceImp() {
        System.out.println("OwnerServiceImpl bean created.");
    }
    @Override
    public String findOwner() {
        return ownerRepository.findOwner(ownerId);
    }
}
