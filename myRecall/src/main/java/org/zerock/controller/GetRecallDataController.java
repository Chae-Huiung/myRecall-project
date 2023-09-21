package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.GetRecallDataService;
import org.zerock.service.GetRecallDataServiceImpl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
@RequestMapping("/")
public class GetRecallDataController {
	@Autowired
	GetRecallDataServiceImpl service;
	


}
