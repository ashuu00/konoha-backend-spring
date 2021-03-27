package com.konoha.konoha_server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Product {
  @Id
  private String id;
  @Indexed
 private String title;
 private String slug;
 private float price;
 private int quantity;
 private float discount;
 private List<String> imageLinks;

// @DBRef
// private List<Category> category;
// @DBRef
// private List<Category> sub;

 //description class needs to be added.
 //add date format for discount date
 //we can create other schema for discount and coupons

}
