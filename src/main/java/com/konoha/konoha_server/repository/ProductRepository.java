package com.konoha.konoha_server.repository;

import com.konoha.konoha_server.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findBySlug(String slug);
    Optional<Product> findByTitle(String title);
}
