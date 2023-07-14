package com.om.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.om.app.model.Mongo;


@Repository
public interface MongoRepo extends MongoRepository<Mongo, Long>{
Mongo findByRackid(Long rackid);
}
