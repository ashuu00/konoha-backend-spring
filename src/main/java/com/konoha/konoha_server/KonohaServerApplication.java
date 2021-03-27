package com.konoha.konoha_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;

@SpringBootApplication
public class KonohaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KonohaServerApplication.class, args);
		System.out.println("This app is working great!!! ");
		System.out.println(System.getProperty("user.dir")+" is the current directory");
		String path=System.getProperty("user.dir")+"/src/main/resources/static/images";
		File file=new File(path);
		boolean bool=file.mkdir();
		if(bool){

			System.out.println("Created directory successfully!");
		}else{
			System.out.println("No path found for dir"+path);
		}
	}
}
