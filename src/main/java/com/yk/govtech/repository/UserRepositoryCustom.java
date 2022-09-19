package com.yk.govtech.repository;

import java.util.List;

import com.yk.govtech.entity.User;

public interface UserRepositoryCustom {
	
	List<User> findByRequestParam(double min, double max, int offset, Integer limit, String sort);

}