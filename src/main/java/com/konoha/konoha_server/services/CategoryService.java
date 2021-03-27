package com.konoha.konoha_server.services;
import com.konoha.konoha_server.models.Category;
import com.konoha.konoha_server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {


    private CategoryRepository categoryRepository;
    private Slug slug;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, Slug slug) {
        this.categoryRepository = categoryRepository;
        this.slug=slug;

    }

    //get all categories
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    //check is exist
    public Optional<Category> existCategory(Category category){

        return categoryRepository.findBySlug(category.getSlug());
    }

    //updates the category
    public Category saveCategory(Category category){
      category.setSlug(slug.setSlugify(category.getName()));
//        Category prev=categoryRepository.findBySlug(parent).orElseThrow();
//        prev.setName(category.getName());
//        prev.setSlug(category.getSlug());
//        prev.setSubs(category.getSubs());
        return categoryRepository.save(category);
    }

    //creates new category with emptylist
    public Category newCategory(Category category){
        category.setSlug(slug.setSlugify(category.getName()));
        category.setSubs(Collections.emptyList());
        return categoryRepository.insert(category);
    }

    //deletes category
    public void deleteCat(String slug){
         categoryRepository.deleteBySlug(slug);

       }



    //removes a single item from the category
    public Category removeItem(String parent, String item){
        Category itemParent=categoryRepository.findBySlug(parent).orElseThrow( NullPointerException::new);
        List<Category> items=itemParent.getSubs();
        List<Category> updated=items.stream().filter(elem->!elem.getSlug().equals(item)).collect(Collectors.toList());
        itemParent.setSubs(updated);
        return itemParent;
    }


    public Category addItem(String parent, String name){
    Category parentItem=categoryRepository.findBySlug(parent).orElseThrow();
    List<Category> items=parentItem.getSubs();
    String slugItem=slug.setSlugify(name);
    Category newItem=new Category(name,slugItem, Collections.emptyList());
    items.add(newItem);
    parentItem.setSubs(items);
    return categoryRepository.save(parentItem); }
}