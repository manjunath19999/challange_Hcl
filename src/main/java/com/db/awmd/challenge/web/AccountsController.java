package com.db.awmd.challenge.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class AccountsController {

	@GetMapping(path = "/wish/{name}")
	public String  getAccount(@PathVariable String name) {
		log.info(name);
		return "hi" + name;
	}

}
