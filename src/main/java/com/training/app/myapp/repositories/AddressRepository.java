package com.training.app.myapp.repositories;

import com.training.app.myapp.entities.AddressEntity;
import com.training.app.myapp.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity,Long> {// we can do pageable one here {

    List<AddressEntity> findAllByUser(UserEntity userEntity);
    AddressEntity findByAddressId(String addressId);
    void deleteByAddressId(String addressId);
}
