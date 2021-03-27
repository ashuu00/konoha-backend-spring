package com.konoha.konoha_server.bucket;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;


@Service
public class ImageStorage {

    //for saving and retrieving data
    private final AmazonS3 amazonS3;

    @Autowired
    public ImageStorage(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    /*Here, we pass Map<String,String> to meta datas such as content-type, size, etc
    InputStream is used to pass the data
     */
    public void save(String path, String fileName,
                     File imageFile){
        try{
            amazonS3.putObject(path,fileName,imageFile);
        }catch(AmazonServiceException e){ // AWS has their own exception handling
            throw new IllegalStateException("Failed to save data"+e);
        }
    }

    // From AWS, we get stream of data, which we need to convert into byte Array for getting it as output
    public byte[] download(String path, String key){
        try{
            S3Object imageObj=amazonS3.getObject(path,key);
            return IOUtils.toByteArray(imageObj.getObjectContent());
        }
        catch (AmazonServiceException| IOException e){
            throw new IllegalStateException(e);
        }
    }

    public void delete(String fullPath, String filename) {
        try {
            amazonS3.deleteObject(fullPath, filename);
        }
        catch(AmazonServiceException e){
            throw new IllegalStateException(e);
        }
    }
}
