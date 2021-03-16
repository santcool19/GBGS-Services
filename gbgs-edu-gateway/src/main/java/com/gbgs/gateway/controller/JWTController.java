package com.gbgs.gateway.controller;

import com.gbgs.gateway.models.UserInfoDTO;
import com.gbgs.gateway.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token/generate")
public class JWTController {

	@Autowired
	private JWTService jwtService;
	
	@PostMapping
	public ResponseEntity<String> generateToken(@RequestBody UserInfoDTO userInfoDTO){
		return ResponseEntity.ok(jwtService.createToken(userInfoDTO.getUserId(), userInfoDTO.getUserType(), userInfoDTO.getUserRole()));
	}
	
}
