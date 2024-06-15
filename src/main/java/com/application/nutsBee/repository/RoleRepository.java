package com.application.nutsBee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.nutsBee.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("SELECT DISTINCT role FROM Role role WHERE role.userId = :userId")
	List<Role> findRoleByUserId(Long userId);
}
