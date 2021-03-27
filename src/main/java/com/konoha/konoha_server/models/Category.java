package com.konoha.konoha_server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Document
public class Category {

    @Id
    private String id;
    @NotNull(message = "Category Name must be defined")
    @Size(min=3,max=50)
    @TextIndexed
    private String name;
    private String slug;
    //private String parent;
    private List<Category> subs;

    public Category(String name, String slug, List<Category> subs) {
        this.name = name;
        this.slug = slug;
        this.subs = subs;
    }
}
