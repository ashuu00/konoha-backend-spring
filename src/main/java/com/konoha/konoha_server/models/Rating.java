package com.konoha.konoha_server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Rating {

    @Id
    private String id;
    @DBRef
    private Product product;
    private float stars;
    private String title;
    private String description;
    private Date ratingDate;
}
