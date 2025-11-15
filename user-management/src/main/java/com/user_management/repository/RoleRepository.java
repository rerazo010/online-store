package com.user_management.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user_management.entity.Role;
import com.user_management.enumeration.ERole;


@Repository
public interface RoleRepository extends JpaRepository<Role, Serializable> {

    // Método para encontrar un rol por su nombre
    Optional<Role> findByName(ERole name);

    // Método para verificar si un rol existe por su nombre
    boolean existsByName(ERole name);

}
