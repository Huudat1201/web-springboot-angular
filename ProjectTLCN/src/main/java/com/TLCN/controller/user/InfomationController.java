package com.TLCN.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.TLCN.common.Constants;
@Controller
public class InfomationController {
	@GetMapping("/aboutUs")
	public String AboutUS() {
		return Constants.USER_DISPLAY_IMFORMATION_ABOUT_US;
	}
	
	@GetMapping("/delivery")
	public String Delivery() {
		return Constants.USER_DISPLAY_IMFORMATION_DELIVERY;
	}
	
	@GetMapping("/policy")
	public String Policy() {
		return Constants.USER_DISPLAY_IMFORMATION_POLICY;
	}
	
	@GetMapping("/termCondition")
	public String TermCondition() {
		return Constants.USER_DISPLAY_IMFORMATION_TERM_CONDITION;
	}
}
