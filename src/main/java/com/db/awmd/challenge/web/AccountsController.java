package com.db.awmd.challenge.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class AccountsController {

	@GetMapping(path = "/wish/{name}")
	public String  getAccount(@PathVariable String name) {
		return "hi " + name ;
	}

}
