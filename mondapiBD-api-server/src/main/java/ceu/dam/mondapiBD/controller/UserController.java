package ceu.dam.mondapiBD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ceu.dam.mondapiBD.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
		
}
