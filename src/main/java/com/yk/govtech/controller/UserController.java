package com.yk.govtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yk.govtech.entity.User;
import com.yk.govtech.model.UploadResponse;
import com.yk.govtech.model.UserRetrievalResponse;
import com.yk.govtech.processor.CSVFileProcessor;
import com.yk.govtech.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	//TODO: add request headers for min/max/offset/limit/sort
	@GetMapping("/users")
	public ResponseEntity<UserRetrievalResponse> getUsers() {
		
		List<User> userList = userService.getUsers();
		
		if (userList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		UserRetrievalResponse results = new UserRetrievalResponse();
		results.setResults(userList);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<UploadResponse> upload(@RequestParam("file") MultipartFile file) {
		
		UploadResponse uploadResponse = new UploadResponse();
		
		if (!CSVFileProcessor.isValidCSVFile(file)) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		userService.upload(file);
		uploadResponse.setSuccess(1);
		return new ResponseEntity<>(uploadResponse, HttpStatus.OK);
	}
	
	

}
