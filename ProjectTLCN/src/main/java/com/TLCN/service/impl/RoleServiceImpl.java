package com.TLCN.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TLCN.dao.RoleDao;
import com.TLCN.entity.Role;
import com.TLCN.service.RoleService;

/*
 * Class trien khai theo interface RoleService, Thao tac voi Class RoleDao de
 * thuc hien cac tac vu tuong ung
 */
@Service
public class RoleServiceImpl implements RoleService {
	// Thong tin role dao
	@Autowired
	RoleDao roleDao;

	/**
	 * Tim kiem role theo id cua role
	 * 
	 * @param thong tin id cua role
	 * @return Role tim duoc
	 */
	@Override
	public Role findRoleById(int roleId) {
		return roleDao.findById(roleId).get();
	}
	
	/**
	 * Tim ten vai tro dua vao user id
	 * 
	 * @param thong tin user id
	 * @return danh sach ten vai tro tim duoc
	 */
	@Override
	public List<String> getRoleNames(int userId) {
		return roleDao.getRoleNames(userId);
	}

}
