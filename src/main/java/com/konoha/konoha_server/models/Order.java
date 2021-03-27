package com.konoha.konoha_server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
@Getter
@Setter
public class Order {
    @Id
    private String id;

    @NotNull@NotEmpty
    private String address;

    @NotNull
    private String phone;

    private String name;
    private String email;

    @DBRef
    private Seller seller;

    @NotNull @NotEmpty
    private float amount;

    private String status;

    @NotEmpty @NotNull
    private String paymentMethod;

    private Date orderDate;
    private Date deliveryDate;

}
