package com.kutluay.ProductManagment.repository;

import com.kutluay.ProductManagment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
