package com.yk.govtech.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yk.govtech.entity.User;

public interface UserService {
	
	List<User> getUsers();

	void upload(MultipartFile file);

}
