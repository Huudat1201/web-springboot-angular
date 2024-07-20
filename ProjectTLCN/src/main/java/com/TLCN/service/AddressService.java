package com.TLCN.service;

import java.util.List;

import com.TLCN.entity.Address;
import com.TLCN.entity.District;
import com.TLCN.entity.Province;
import com.TLCN.entity.Ward;
import com.TLCN.model.AddressModel;


public interface AddressService {	
	List<Address> findListAddressByEmail(String username);
	void saveAddress(Address address);
	List<Province> findAllProvince();
	List<District> findDistrictByIdProvince(Integer id);
	List<Ward> findWardByIdProvinceAndIdDistrict(Integer idProvince, Integer idDistrict);
	AddressModel createAddress(AddressModel addressModel);
	void createAddress2(Address address);
	Address getAddressById(int parseInt);
	void delete(Address address);
	Address findAddressById(String username, int id);
	AddressModel getOneAddressById(int id);
	List<District> getListDistrictByAdressId(Integer id);
	List<Ward> getListWardByAdressId(Integer id);
	AddressModel updateAddress(AddressModel addressModel, Integer id);
}
