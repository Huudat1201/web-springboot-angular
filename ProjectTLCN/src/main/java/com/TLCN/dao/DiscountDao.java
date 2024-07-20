package com.TLCN.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TLCN.entity.Discount;
public interface DiscountDao extends JpaRepository<Discount, Integer>{
	@Query("SELECT d FROM Discount d WHERE d.Deleteday = null")
	List<Discount> getListDiscount();
	
	@Query(value="SELECT * FROM tlcn.discount where deleteday is Null and Code LIKE ?1", nativeQuery = true)
	Discount getDiscountByCode(String code);
	
	@Query(value="SELECT * FROM discount where deleteday is Null;", nativeQuery = true)
	List<Discount> getListDiscountAvailable();
}
