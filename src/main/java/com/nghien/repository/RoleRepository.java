package com.nghien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghien.entities.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role,String> {
}
