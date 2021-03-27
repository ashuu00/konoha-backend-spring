package com.konoha.konoha_server.controllers;

import com.konoha.konoha_server.models.Product;
import com.konoha.konoha_server.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private ProductService productsService;

    @Autowired
    public ProductController(ProductService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Product> getAll(){
        return Collections.emptyList() ;//productsService.getAll();
    }

    @GetMapping("/{slug}")
    @ResponseBody
    public Product getSingle(@PathVariable("slug") String productSlug){
        return  new Product(); //getProduct(productSlug).orElseThrow();
    }

    @PostMapping("/create")
    @ResponseBody
    public String createOne(@RequestBody Product product){
       return productsService.uploadProductData(product);
    }

    @PutMapping("/update")
    @ResponseBody
    public Product updateOne(@RequestBody Product product){
        return productsService.updateProduct(product);
    }

    //here we take images, store them on cloud and then send back the link
    //defining that we would get multipart in the request.
    @PostMapping(path="/uploadimages/{productSlug}",
                            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  void storeCloud(@PathVariable("productSlug") String productSlug, @RequestParam("images") List<MultipartFile> images) throws IOException {
            productsService.uploadProductImages( productSlug, images);
    }

    @GetMapping( "/getimages/{productSlug}")
    @ResponseBody
    public List<byte[]> allProductImages(@PathVariable String productSlug){
        return productsService.downloadProductImages(productSlug);
    }

    @DeleteMapping("/deleteimages/{productSlug}")
    @ResponseBody
    public String deleteProductImages(@PathVariable String productSlug,@RequestParam("imageLinks") List<String> images) {
        return productsService.deleteProductImages(productSlug, images);
    }
}