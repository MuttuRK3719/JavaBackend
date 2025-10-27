package org.springbootexample.service;

import org.springbootexample.exception.OwnerNotFoundException;

public interface OwnerService {
    String findOwner() throws OwnerNotFoundException;

    void modifyOwnerId(int ownerId);
}
