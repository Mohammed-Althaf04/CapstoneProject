package com.wip.smartparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wip.smartparking.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}