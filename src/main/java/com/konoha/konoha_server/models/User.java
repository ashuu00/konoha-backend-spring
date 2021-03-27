package com.konoha.konoha_server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class User {

    private String name;
    private String slug;
    private String phone;
    private String email;
    private List<String> address;

    @DBRef
    private List<Order> orderHistory;

    @DBRef
    private List<Product> cartItems;

    @DBRef
    private List<Product> wishList;

    //one more saving liked blogs

    private List<String> searchResults;

}
