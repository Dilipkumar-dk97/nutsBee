package com.application.nutsBee.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.application.nutsBee.Entity.Address;
import com.application.nutsBee.service.AddressService;

@RestController
@RequestMapping("/nutsBee")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/address")
	public ResponseEntity<Address> addAddress(@RequestParam Long userId, @RequestBody Address addressBody) {
		Address address = addressService.addAddress(userId,addressBody);
		if (address == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(address);
	}
	
	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAddressList(@RequestParam Long userId) {
		List<Address> address = addressService.getAddressList(userId);
		if (address == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(address);
	}
	
	@DeleteMapping("/address/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
		addressService.deleteAddress(addressId);
		return ResponseEntity.ok("Deleted SuccessFully");
	}
	
	@PatchMapping("/address/{addressId}")
	public ResponseEntity<Address> patchAddressById(@PathVariable Long addressId, @RequestBody Map<String,String> data) {
		Address address = addressService.patchAddressById(addressId,data);
		if (address == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(address);
	}
}
