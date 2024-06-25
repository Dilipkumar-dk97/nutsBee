package com.application.nutsBee.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
		HttpHeaders responseHeaders = new HttpHeaders();
		if (address == null) {
			return new ResponseEntity<>(responseHeaders,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(address,responseHeaders,HttpStatus.CREATED);
	}
	
	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAddressList(@RequestParam Long userId) {
		List<Address> address = addressService.getAddressList(userId);
		HttpHeaders responseHeaders = new HttpHeaders();
		if (address == null) {
			return new ResponseEntity<>(responseHeaders,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(address,responseHeaders,HttpStatus.OK);
	}
	
	@DeleteMapping("/address/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
		addressService.deleteAddress(addressId);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>("Deleted SuccessFully",responseHeaders,HttpStatus.OK);
	}
	
	@PatchMapping("/address/{addressId}")
	public ResponseEntity<Address> patchAddressById(@PathVariable Long addressId, @RequestBody Map<String,String> data) {
		Address address = addressService.patchAddressById(addressId,data);
		HttpHeaders responseHeaders = new HttpHeaders();
		if (address == null) {
			return new ResponseEntity<>(responseHeaders,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(address,responseHeaders,HttpStatus.OK);
	}
}
