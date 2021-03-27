package com.konoha.konoha_server.services;
import com.konoha.konoha_server.bucket.ImageStorage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class DataService {

    private  final ImageStorage imageStorage;

    public DataService(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }

    public List<String> uploadImages(String path, List<MultipartFile> images){
        //check if list not empty, path would be the category title slugified.
        if(images.isEmpty()){
            throw new IllegalStateException("There are no images inside it");
        }
        List<String> imagesLink=new ArrayList<>();
        // now checking for each image and applying queries
        images.forEach(image->{
            if(image.isEmpty()){
                throw new IllegalStateException("Cannot store empty file ["+image.getSize()+"]");
            }
            //checks if the image of type JPEG, PNG, GIF or WEBP
            isImageEmpty(image);
            // Adding meta-data inside our S3 folder while saving
            //Map<String, String> metaData = extractMetaData(image);
            /*Now, we can store in firebase
            * 1) Get the path, then generate random filename, return the filename to download it later  */
            String fileName=String.format("%s-%s",image.getOriginalFilename(),UUID.randomUUID());
            try {
                File imageFile=convertMultiPartToFile(image);
                 imageStorage.save(path, fileName, imageFile);
                 imagesLink.add(fileName);
            } catch (IOException e){
                throw new IllegalStateException();
            }
        });
        return imagesLink;
    }

    // We will use downloadImage everytime we want to download an image, use in loop for multiple
    public byte[]  downloadImage(String bucketname,String path, String key){
            String fullPath=String.format("%s/%s",bucketname,path);
            return imageStorage.download(fullPath,key);
    }

    public void deleteImage(String bucket,String path, String filename){
        String fullPath=String.format("%s/%s",bucket, path);
         imageStorage.delete(fullPath,filename);
    }



    private void isImageEmpty(MultipartFile image) {
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_WEBP.getMimeType(),IMAGE_GIF.getMimeType(),IMAGE_PNG.getMimeType(),IMAGE_SVG.getMimeType())
                .contains(image.getContentType())){
                throw new IllegalStateException("Expected an image");
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}


