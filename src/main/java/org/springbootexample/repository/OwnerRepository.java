package org.springbootexample.repository;

import org.springbootexample.exception.OwnerNotFoundException;

public interface OwnerRepository {
    String findOwner(int ownerId)throws OwnerNotFoundException;;
}
