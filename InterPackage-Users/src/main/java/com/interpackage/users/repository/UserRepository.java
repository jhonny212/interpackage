package com.interpackage.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.interpackage.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
