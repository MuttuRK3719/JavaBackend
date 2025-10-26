package org.springexample.service.mock;

import org.springexample.service.OwnerService;

public class OwnerServiceImp implements OwnerService {
    private int ownerId;

    public OwnerServiceImp(int ownerId) {
        this.ownerId = ownerId;
        System.out.println("OwnerServiceImpl mock bean created.");
    }

    @Override
    public String findOwner() {
        return String.format("Found owner from service with ownerId %s", ownerId);
    }
}
