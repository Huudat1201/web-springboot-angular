package com.TLCN.service;

import java.util.List;

import com.TLCN.entity.User;
import com.TLCN.model.ChangePassModel;
import com.TLCN.model.InformationModel;

//Class cung cap cac dich vu thao tac voi table Users trong database

public interface UserService {
	
	/**
	 * Tim kiem User bang email
	 * 
	 * @param username thong tin email
	 * @return User tim duoc
	 */
	User findUserByEmail(String email);

	void save(User user);

	List<User> findAll();

	User create(User user);

	List<User> findAllUserAnable();

	InformationModel getUserAccount();

	InformationModel update(InformationModel informationModel);

	ChangePassModel updatePass(ChangePassModel changePassModel);

	User findById(Integer id);

	InformationModel createUser(InformationModel informationModel);

	List<User> findAllUserNotRoleAdmin();

	void deleteUser(Integer id);

	InformationModel getOneUserById(Integer id);

	InformationModel updateUser(InformationModel informationModel, Integer id);

	List<User> getListUserEnableSubscribe();

}
