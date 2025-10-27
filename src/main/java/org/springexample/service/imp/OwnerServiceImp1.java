package org.springexample.service.imp;

import org.springexample.repository.OwnerRepository;
import org.springexample.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("specialService")
public class OwnerServiceImp1 implements OwnerService {
    @Qualifier("specialRepository")
    @Autowired
    private OwnerRepository ownerRepository;
    @Value("20")
    private int ownerId;

    public OwnerServiceImp1() {
        System.out.println("OwnerServiceImpl special bean created.");
    }

    @Override
    public String findOwner() {
        return ownerRepository.findOwner(ownerId);
    }
}
