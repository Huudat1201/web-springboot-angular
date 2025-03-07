package com.TLCN.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.TLCN.entity.Order;
import com.TLCN.entity.Product;
import com.TLCN.model.BestSellerModel;
import com.TLCN.model.DetailOrder;
import com.TLCN.model.OrderModel;
import com.TLCN.model.StatisticalProductDay;
import com.TLCN.model.StatisticalRevenue;
import com.TLCN.model.StatisticalTotalOrder;

public interface OrderService {

	List<Order> getOrderByName(String code);

	void save(Order order);

	List<OrderModel> listOrderHistory();

	List<Order> listOrderByCodeAndUsername(String id);

	List<OrderModel> listOrderGroupByCode();

	DetailOrder getDetailOrderByCode(String id);

	void approveOrder(String id);

	void cancelOrder(String id);

	List<OrderModel> listOrderGroupByCodeShipping();

	void shippedOrder(String id);

	List<OrderModel> listOrderGroupByCodeSuccess();

	List<OrderModel> listOrderGroupByCodeCancel();

	void deleteOrder(String id);

	List<StatisticalProductDay> listStatisticalProductDay();
	
	List<StatisticalRevenue> listStatisticalRevenue(int month, int year);

	List<StatisticalRevenue> listStatisticalRevenueByMonth(int year);

	List<StatisticalRevenue> listStatisticalRevenueByYear(int year);

	StatisticalTotalOrder getStatisticalTotalOrderOnDay(int day, int month, int year);

	StatisticalTotalOrder getStatisticalTotalOrderOnMonth(int month, int year);

	StatisticalTotalOrder getStatisticalTotalOrderOnYear(int year);

	List<Integer> getListYearOrder();

	StatisticalTotalOrder getStatisticalTotalOrderOnOption(int day, int month, int year);

	List<BestSellerModel> getListBestSellerProduct(Pageable topFour);

	List<Product> listStatisticalProductWarehouse();

}
