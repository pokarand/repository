package com.appsdeveloper.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.appsdeveloper.app.ws.exceptions.UserServiceException;
import com.appsdeveloper.app.ws.ui.model.request.UpdateUserDetailRequestModel;
import com.appsdeveloper.app.ws.ui.model.request.UserDetailRequestModel;
import com.appsdeveloper.app.ws.ui.model.response.UserRest;
import com.appsdeveloper.app.ws.userservice.UserService;
import com.appsdeveloper.app.ws.userservice.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")//http://localhost:8080/users/
public class UserController {
	
	@Autowired
	UserService userServiceImpl;
	
	
	Map<String, UserRest> users = new HashMap<>();
	
	@GetMapping
	public String getUsers(@RequestParam(value = "page",defaultValue = "1") int page,
			@RequestParam(value = "limit",defaultValue = "60") int limit,
			@RequestParam(value = "sort",defaultValue = "desc",required  = false) String sort)
	{
		
		return "get user was called with::"+page+" and limit1s :: "+limit+" and sort :: "+sort;
	}

	
	@GetMapping(path = "/{userId}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)
	{
		/*UserRest userRest = new UserRest();
		userRest.setFirstName("Pooja");
		userRest.setLastName("Karande");
		userRest.setEmail("pooja.egov@gmail.com");*/
		//userRest.setUserId("111");
		
		if(true) throw new UserServiceException("A user service exception throw");
		
		if (users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId) ,HttpStatus.OK);

		}else {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
			                 MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_XML_VALUE,
			    		     MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailRequestModel requestModel) {
		
		UserRest userRest = userServiceImpl.createUser(requestModel);
		
		return new ResponseEntity<UserRest>(userRest,HttpStatus.OK);
	}
	
	

	@PutMapping(path = "/{userId}",consumes = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_XML_VALUE,
		                    MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String userId,@Valid @RequestBody UpdateUserDetailRequestModel requestModel) {
		
		UserRest  storeUserDetails = users.get(userId);
		storeUserDetails.setFirstName(requestModel.getFirstName());
		storeUserDetails.setLastName(requestModel.getLastName());
		users.put(userId, storeUserDetails);
		
		
		
		return storeUserDetails;
	}
	
	

	@DeleteMapping(path = "/{Id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String Id) {
		
		users.remove(Id);
		
		return ResponseEntity.noContent().build();
	}
	
}
