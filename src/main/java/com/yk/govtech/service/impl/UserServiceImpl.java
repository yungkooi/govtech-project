package com.yk.govtech.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yk.govtech.entity.User;
import com.yk.govtech.processor.CSVFileProcessor;
import com.yk.govtech.repository.UserRepository;
import com.yk.govtech.repository.custom.UserRepositoryCustom;
import com.yk.govtech.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRepositoryCustom userRepositoryCustom;

	@Override
	public List<User> getUsersByRequestParam(double min, double max, int offset, Integer limit, String sort) {
		
		return userRepositoryCustom.findByRequestParam(min, max, offset, limit, sort);
	}

	@Override
	public void upload(MultipartFile file) {
		try {
			List<User> users = CSVFileProcessor.processCSVFile(file.getInputStream());
			userRepository.saveAll(users);
		} catch (IOException e) {
			throw new RuntimeException("Unable to store csv file data: " + e.getMessage());
		}
	}

}
