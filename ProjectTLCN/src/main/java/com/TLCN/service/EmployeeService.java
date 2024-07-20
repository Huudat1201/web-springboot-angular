package com.TLCN.service;

import java.util.List;

import com.TLCN.entity.Employee;
import com.TLCN.model.EmployeeModel;

//Class cung cap cac dich vu thao tac voi table Employee trong database

public interface EmployeeService {

	List<EmployeeModel> getListEmployee();

	void save(Employee employee);
	
}
