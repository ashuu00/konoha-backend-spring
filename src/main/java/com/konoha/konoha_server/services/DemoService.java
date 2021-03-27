package com.konoha.konoha_server.services;

import com.konoha.konoha_server.models.User;
import com.konoha.konoha_server.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {
    @Autowired
    DemoRepository demoRepository;

    public List<User> getAll(){
        return demoRepository.findAll();
    }
}
