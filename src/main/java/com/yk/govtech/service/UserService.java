package com.yk.govtech.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yk.govtech.entity.User;

public interface UserService {
	
	List<User> getUsersByRequestParam(double min, double max, int offset, Integer limit, String sort);

	void upload(MultipartFile file);

}
