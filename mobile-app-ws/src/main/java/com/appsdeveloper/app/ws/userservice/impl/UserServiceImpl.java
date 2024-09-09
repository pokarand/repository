package com.appsdeveloper.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloper.app.ws.shared.Utils;
import com.appsdeveloper.app.ws.ui.model.request.UserDetailRequestModel;
import com.appsdeveloper.app.ws.ui.model.response.UserRest;
import com.appsdeveloper.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
	Map<String, UserRest> users = new HashMap<>();
	Utils utils;
	
	@Autowired
	public UserServiceImpl(Utils utils) {
	
		this.utils = utils;
	}
	
	@Override
	public UserRest createUser(UserDetailRequestModel requestModel) {
		// TODO Auto-generated method stub
		
		UserRest userRest = new UserRest();
		userRest.setFirstName(requestModel.getFirstName());
		userRest.setLastName(requestModel.getLastName());
		userRest.setEmail(requestModel.getEmail());
		
		//String userId = UUID.randomUUID().toString();
		String userId = utils.generateUserId();
		userRest.setUserId(userId);
		
		if(userId == null) users = new  HashMap<>();
		users.put(userId, userRest);
		
		return userRest;
	}

}
