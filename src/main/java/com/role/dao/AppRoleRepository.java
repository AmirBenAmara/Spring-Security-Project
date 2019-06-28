package com.role.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.role.entities.AppRole;
@RepositoryRestResource
public interface AppRoleRepository  extends JpaRepository<AppRole, Long> {
	   AppRole findByRoleName(String roleName);

}
