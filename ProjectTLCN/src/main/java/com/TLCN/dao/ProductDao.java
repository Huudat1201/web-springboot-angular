package com.TLCN.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TLCN.entity.Product;

/**
 * Class thuc hien truy van thong tin bang Product trong database
 */
public interface ProductDao extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null")
	List<Product> getListProduct();

	@Query(value="SELECT * FROM Products WHERE deleteDay IS NULL AND Active = 1 ORDER BY CreateDay DESC LIMIT 16;", nativeQuery = true)
	List<Product> getListLatestProduct();

	@Query(value="SELECT * FROM Products WHERE DeleteDay IS NULL AND Active = 1 ORDER BY Views DESC LIMIT 14", nativeQuery = true)
	List<Product> getListViewsProduct();
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 ORDER BY p.Createday DESC")
	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 AND p.price >= ?2 AND p.price <= ?3 ORDER BY p.Createday DESC")
	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null")
	List<Product> getListDemo(String nameSearch);
	
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null AND p.Namesearch LIKE ?1")
	Product getProductByNameSearch(String nameSearch);

	@Query(value="SELECT * FROM Products WHERE DeleteDay IS NULL AND Active = 1 AND Cate_Id = ? ORDER BY Views DESC LIMIT 10", nativeQuery = true)
	List<Product> getListProductRelated(int manuId);

	@Query(value="SELECT * FROM Products WHERE deleteDay IS NULL AND Active = 1 AND Sales != 0 ORDER BY Views DESC LIMIT 5", nativeQuery = true)
	List<Product> getListProductSales();

	@Query(value="SELECT * FROM tlcn.products where quality > 0 and deleteday IS NULL;", nativeQuery = true)
	List<Product> listStatisticalProductWarehouse();
}
