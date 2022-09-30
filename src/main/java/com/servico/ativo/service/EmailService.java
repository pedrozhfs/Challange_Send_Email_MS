package com.servico.ativo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.servico.ativo.model.EmailModel;
import com.servico.ativo.model.StatusEmail;
import com.servico.ativo.repository.EmailRepository;
import org.springframework.data.domain.Pageable;

@Service
public class EmailService {

	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@SuppressWarnings("finally")
	public EmailModel sendEmail(EmailModel model) {
		
		System.out.println("Enviando e-mail");
		
		model.setSendDateEmail(LocalDateTime.now());
		
		try {
			SimpleMailMessage message = new SimpleMailMessage(); 
			
			message.setFrom(model.getEmailFrom());
			message.setTo(model.getEmailTo());
			message.setSubject(model.getSubject());
			message.setText(model.getText());
						
			emailSender.send(message);
			System.out.println("ENTROU NO SEND");
			model.setStatusEmail(StatusEmail.SEND);
			
		} catch (Exception e) {
			System.out.println("ENTROU NO SEND");
			model.setStatusEmail(StatusEmail.SEND);
		} finally {
			return emailRepository.save(model);
		}
	}
	
	public Page<EmailModel> findAll(Pageable pageable) {
        return  emailRepository.findAll(pageable);
    }
	
	public Optional<EmailModel> findById(String id) {
        return emailRepository.findById(id);
    }
}
