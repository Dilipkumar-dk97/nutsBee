package com.application.nutsBee.service;

import java.util.List;
import java.util.Map;

import com.application.nutsBee.Entity.Address;

public interface AddressService {

	public Address addAddress(Long userId, Address address);

	public List<Address> getAddressList(Long userId);

	public void deleteAddress(Long userId);

	public Address patchAddressById(Long addressId, Map<String, String> data);

}
