package com.konoha.konoha_server.repository;

import com.konoha.konoha_server.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DemoRepository extends MongoRepository<User,String> {
}
