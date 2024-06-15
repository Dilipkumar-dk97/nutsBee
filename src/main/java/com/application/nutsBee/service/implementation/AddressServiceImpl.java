package com.application.nutsBee.service.implementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.nutsBee.Entity.Address;
import com.application.nutsBee.repository.AddressRepository;
import com.application.nutsBee.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Address addAddress(Long userId, Address address) {
		address.setUserId(userId);
		return addressRepository.save(address);
	}

	@Override
	public List<Address> getAddressList(Long userId) {
		return addressRepository.findAddressByUserId(userId);
	}

	@Override
	public void deleteAddress(Long addressId) {
		addressRepository.deleteById(addressId);
	}

	@Override
	public Address patchAddressById(Long addressId, Map<String, String> data) {
	Address address = addressRepository.findById(addressId).get();
	updateAddress(address, data);
		return addressRepository.save(address);
	}

	private void updateAddress(Address address, Map<String, String> data) {
		data.forEach((key, value) -> {
	            switch (key) {
	                case "flatNo":
	                	address.setFlatNo(value);
	                    break;
	                case "street":
	                	address.setStreet(value);
	                    break;
	                case "pincode":
	                	address.setPincode(value);
	                    break;
	                case "area":
	                	address.setArea(value);
	                    break;
	                case "city":
	                	address.setCity(value);
	                    break;
	                case "state":
	                	address.setState(value);
	                    break;
	            }
	        });
	}

}
