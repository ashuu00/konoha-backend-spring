package com.konoha.konoha_server.bucket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    //Stored
    PRODUCT_IMAGES("konoha-product");

    private final  String bucketName;


}
