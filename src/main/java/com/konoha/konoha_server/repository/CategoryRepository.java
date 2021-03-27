package com.konoha.konoha_server.repository;

import com.konoha.konoha_server.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {




    Optional<Category> findBySlug(String slug);
    void deleteBySlug(String slug);
    void deleteBySubsSlug(String subSlug);
}
