package com.parkapi.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkapi.park.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
