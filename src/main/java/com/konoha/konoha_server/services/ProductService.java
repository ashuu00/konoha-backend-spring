package com.konoha.konoha_server.services;


import com.konoha.konoha_server.bucket.BucketName;
import com.konoha.konoha_server.models.Product;
import com.konoha.konoha_server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private ProductRepository productRepository;
    private DataService dataService;
    private final String productBucket=BucketName.PRODUCT_IMAGES.getBucketName();
    private Slug slug;

    @Autowired
    public ProductService(ProductRepository productRepository, DataService dataService, Slug slug) {
        this.productRepository = productRepository;
        this.dataService=dataService;
        this.slug = slug;
    }


    //while uploading new data, 1) 1st call this method before posting for image.
    // 2) Call imageuploader if done with this.
    public String uploadProductData(Product product){
        //check slug is unique
        Optional<Product> isExist=productRepository.findByTitle(product.getTitle());
        if(isExist.isPresent()){
            throw new IllegalStateException("Title must be unique");
        }
        //slugy the title to be used when requesting at any other time.
        product.setSlug(slug.setSlugify(product.getTitle()));
        productRepository.insert(product);
        return "Success";
    }

    public void uploadProductImages(String productSlug, List<MultipartFile> images) {
        //get the user first
        System.out.println("Product Slug is"+productSlug);
        Product curr = productRepository.findBySlug(productSlug).orElseThrow(IllegalStateException::new);
        System.out.println(productBucket);
        String completePath=String.format("%s/%s",productBucket,productSlug);
        List<String> imageLinks = dataService.uploadImages(completePath,images);
        curr.setImageLinks(imageLinks);
    }

    public List<byte[]> downloadProductImages(String productSlug){
        // get all users image-link
        List<byte[]> imageOutput=new ArrayList<>();
        // get all the links from the user's database
        Product curr=productRepository.findBySlug(productSlug).orElseThrow(IllegalStateException::new);
                List<String> Links=curr.getImageLinks();
                String folderName=curr.getSlug();
        Links.forEach(key->{
            byte[] image=dataService.downloadImage(productBucket,folderName,key);
            imageOutput.add(image);
        });
        return imageOutput;
    }

    // Updating products
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public String deleteProductImages(String productSlug, List<String> images) {
        // Full path created in data-service
        images.forEach(link->{
            dataService.deleteImage(productBucket,productSlug,link);
        });
        return "Deleted Images successfully";
    }
}
