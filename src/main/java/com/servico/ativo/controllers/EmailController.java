package com.servico.ativo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.servico.ativo.model.EmailModel;
import com.servico.ativo.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/send-email")
	public ResponseEntity<EmailModel> sendEmail(@RequestBody EmailModel model) {
		emailService.sendEmail(model);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	 @GetMapping("/emails")
	    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
	        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
	    }

}
