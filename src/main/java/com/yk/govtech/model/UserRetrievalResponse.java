package com.yk.govtech.model;

import java.util.List;

import com.yk.govtech.entity.User;

import lombok.Data;

@Data
public class UserRetrievalResponse {
	
	public List<User> results;

}
