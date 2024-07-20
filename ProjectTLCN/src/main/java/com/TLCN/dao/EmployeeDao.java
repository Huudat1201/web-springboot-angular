package com.TLCN.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TLCN.entity.Employee;
import com.TLCN.model.EmployeeModel;

/**
 * Class thuc hien truy van thong tin bang Employee trong database
 */
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	@Query("SELECT new EmployeeModel(e.user.Fullname, e.department, e.phone, e.position, e.user.birthday, e.Startday, e.salary) FROM Employee e WHERE e.Deleteday = null")
	List<EmployeeModel> getListEmployee();
	
	@Query("SELECT e FROM Employee e WHERE e.user.id = :uid")
	Employee getEmployeeByUserId(@Param("uid") Integer id);
}
