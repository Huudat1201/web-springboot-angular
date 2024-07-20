package com.TLCN.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.TLCN.common.Constants;
import com.TLCN.entity.Address;
import com.TLCN.service.AddressService;


@Controller
public class AddressController {
	@Autowired
	AddressService addressService;
	
	@PostMapping("/saveaddress")
	public String saveEmployee(@ModelAttribute("address") Address address) {
		// save employee to database
		//addressService.saveAddress(address);
		addressService.createAddress2(address);
		return "redirect:/account/address";
	}
	
	@GetMapping("/account/address/add")
	public String add(Model model) {
		//model.addAttribute("enableBtnUpdate", false);
		//return "addaddress";
		Address address = new Address();
		model.addAttribute("address", address);
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS_ADD2;
	}
	
	@GetMapping("/account/address")
	public String index() {
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS;
	}
	
	@GetMapping("/account/address/delete/{id}")
	public String add(@PathVariable("id") int id) {
		Address address = addressService.getAddressById(id);
		
		addressService.delete(address);
		
		return "redirect:/account/address";
	}
	
	@GetMapping("/account/address/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Address address = addressService.findAddressById(username, id);
		
		if(address == null) {
			return "redirect:/account/address";
		}
		addressService.saveAddress(address);
		model.addAttribute("address", address);
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS_ADD2;
	}
	
	@ModelAttribute("listAddress")
	public List<Address> getListAddress(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		return addressService.findListAddressByEmail(username);
	}
	
}
