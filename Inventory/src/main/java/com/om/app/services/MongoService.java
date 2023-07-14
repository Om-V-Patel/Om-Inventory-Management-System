package com.om.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.om.app.model.Mongo;

@Service
public class MongoService {

	@Autowired
	private com.om.app.repo.MongoRepo MongoRepo;
		
	public void savedata(Mongo Mongo) {
		MongoRepo.save(Mongo);	
	}

}
