package com.role.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.role.entities.AppUser;
@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
   AppUser findByUsername(String username);
}
