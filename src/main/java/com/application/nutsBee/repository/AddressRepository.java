package com.application.nutsBee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.nutsBee.Entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{
	@Query("SELECT DISTINCT address FROM Address address WHERE address.userId = :userId")
	List<Address> findAddressByUserId(Long userId);
}
