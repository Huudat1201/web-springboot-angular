package com.TLCN.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TLCN.entity.Manufacturer;

public interface ManufacturerDao extends JpaRepository<Manufacturer, Integer>{
	@Query("SELECT m FROM Manufacturer m WHERE m.Deleteday IS NULL ORDER BY m.id")
	List<Manufacturer> getListManufacturer();
}
