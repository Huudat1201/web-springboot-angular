package com.TLCN.service;

import com.TLCN.model.EmployeeForm;

public interface GeneralService {

	EmployeeForm createEmployee(EmployeeForm employee);

	EmployeeForm getOneUserById(Integer id);

	EmployeeForm updateEmployee(EmployeeForm employeeForm);

}
