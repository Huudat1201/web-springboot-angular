package com.TLCN.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TLCN.dao.EmployeeDao;
import com.TLCN.entity.Employee;
import com.TLCN.model.EmployeeModel;
import com.TLCN.service.EmployeeService;

/*
Class trien khai theo interface EmployeeService, Thao tac voi Class EmployeeDao de thuc hien cac tac vu tuong ung
*/
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public List<EmployeeModel> getListEmployee() {
		// TODO Auto-generated method stub
		return employeeDao.getListEmployee();
	}

	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

}
