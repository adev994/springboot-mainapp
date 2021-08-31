package com.training.app.myapp.repositories;

import com.training.app.myapp.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);

    @Query(value="select * from users WHERE users.user_name='sqhd667mmLL'",nativeQuery = true)
    Page<UserEntity> findAllByuserName(Pageable pageableRequest);
}
