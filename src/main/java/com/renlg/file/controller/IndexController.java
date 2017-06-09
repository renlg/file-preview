package com.renlg.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.renlg.base.BaseController;

@Controller
public class IndexController extends BaseController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}
}
