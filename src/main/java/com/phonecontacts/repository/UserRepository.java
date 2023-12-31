package com.phonecontacts.repository;

import com.phonecontacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

}
