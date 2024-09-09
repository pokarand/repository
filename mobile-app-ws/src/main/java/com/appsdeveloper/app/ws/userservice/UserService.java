package com.appsdeveloper.app.ws.userservice;

import org.springframework.stereotype.Service;

import com.appsdeveloper.app.ws.ui.model.request.UserDetailRequestModel;
import com.appsdeveloper.app.ws.ui.model.response.UserRest;


public interface UserService {
	
	UserRest createUser(UserDetailRequestModel requestModel);
	

}
