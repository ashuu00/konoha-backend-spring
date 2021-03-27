package com.konoha.konoha_server.controllers;

import com.github.slugify.Slugify;
import com.konoha.konoha_server.Exception.ApiException;
import com.konoha.konoha_server.models.Category;
import com.konoha.konoha_server.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService; }

    @GetMapping("/all")
    @ResponseBody
    public List<Category> showAll(){
        System.out.println("Inside get all");
      return  categoryService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        Optional<Category> getOne=categoryService.existCategory(category);
        if(getOne.isPresent()){throw  new ApiException();}
        return new ResponseEntity<>(categoryService.newCategory(category), HttpStatus.ACCEPTED);
    }

    @PostMapping("/createsub/{parent}")
    @ResponseBody
    public Category addSub(@PathVariable("parent") String parent, @RequestBody String name){
        return categoryService.addItem(parent, name);
    }
//    @PostMapping("/choice")
//    @ResponseBody
//    public Category addChoice(@RequestBody String name, @RequestParam("parent") String slug){
//        return
//    }

    @PutMapping("/update")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Category putCategory( @RequestBody Category category){
        return categoryService.saveCategory( category);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delCategory(@RequestParam("id") String id){
         categoryService.deleteCat(id);
         return "Deleted Category!!";
    }

    @DeleteMapping("/subdelete")
    @ResponseBody
    public Category delSub(@RequestParam Map<String,String> allParams){
        String parent=allParams.get("parent");
        String item=allParams.get("item");
       return  categoryService.removeItem(parent,item);

    }
//    @GetMapping("/sub")
//    @ResponseBody
//    public List<Category> getSubCategory(@RequestParam("name") String parent){
//        return categoryService.getSubs(parent);
//    }
}
