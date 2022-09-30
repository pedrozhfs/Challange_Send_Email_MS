package com.servico.ativo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.servico.ativo.model.EmailModel;

public interface EmailRepository extends MongoRepository<EmailModel, String>{
	 
}
