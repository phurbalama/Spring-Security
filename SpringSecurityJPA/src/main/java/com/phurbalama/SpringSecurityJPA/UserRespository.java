package com.phurbalama.SpringSecurityJPA;

import com.phurbalama.SpringSecurityJPA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*

 */
public interface UserRespository extends JpaRepository<User,Long> {
    //asking JPA to find User using provided userName. JPA provides the implementation
    Optional<User> findByUserName(String userName);
}
