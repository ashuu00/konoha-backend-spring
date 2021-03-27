package com.konoha.konoha_server.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//All the AWS configuration would be done here

@Configuration
public class AmazonConfig {

        @Value("${aws.access_id}")
        private String awsAccessId;
        @Value("${aws.access_secret}")
        private String awsAccessKey;
        @Value("${aws.region}")
        private String region;

        // Creating AmazonS3 Instance with Configuration
        @Bean
        public AmazonS3 S3() {
            System.out.println("aws secret is"+awsAccessKey);
            AWSCredentials awsCredentials = new BasicAWSCredentials(
                    awsAccessId,
                    awsAccessKey

            );
            return AmazonS3ClientBuilder.standard()
                        .withRegion(Regions.AP_SOUTH_1)//Regions.fromName(region))
                        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                        .build();
        }


}

