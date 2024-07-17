package com.example.security.security.repository;

import com.example.security.security.models.user_roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepo extends JpaRepository<user_roles, Long> {

}
