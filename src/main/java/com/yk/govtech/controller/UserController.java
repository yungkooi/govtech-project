package com.yk.govtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yk.govtech.entity.User;
import com.yk.govtech.exception.BaseException;
import com.yk.govtech.model.BasicResponse;
import com.yk.govtech.model.UploadResponse;
import com.yk.govtech.processor.CSVFileProcessor;
import com.yk.govtech.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<BasicResponse> getUsers(
			@RequestHeader(value = "min", defaultValue = "0") double min,
			@RequestHeader(value = "max", defaultValue = "4000") double max,
			@RequestHeader(value = "offset", defaultValue = "0") int offset,
			@RequestHeader(value = "limit", required = false) Integer limit,
			@RequestHeader(value = "sort", required = false) String sort
			) {
		
		List<User> userList = userService.getUsersByRequestParam(min, max, offset, limit, sort);
		
		if (userList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setResults(userList);
		return new ResponseEntity<>(basicResponse, HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<UploadResponse> upload(@RequestParam("file") MultipartFile file) {
		
		UploadResponse uploadResponse = new UploadResponse();
		
		if (!CSVFileProcessor.isValidCSVFile(file)) {
			throw new BaseException("Invalid file type");
		}

		userService.upload(file);
		uploadResponse.setSuccess(1);
		return new ResponseEntity<>(uploadResponse, HttpStatus.OK);
	}
	
	

}
