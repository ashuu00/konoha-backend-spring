package com.konoha.konoha_server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Seller {
    @Id
    private String id;

    private String name;
    private String email;
    private String slug;
    private String company;
    private String phone;
    private List<String> zipcodes;
    private List<Order> orders;


}
