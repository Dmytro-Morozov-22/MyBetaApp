package com.ua.vinn.MyBetaApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@SpringBootApplication(scanBasePackages = {"com.ua.vinn.MyBetaApp"})
//@EnableMongoRepositories({"com.ua.vinn.MyBetaApp"})
@SpringBootApplication
public class MyBetaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBetaAppApplication.class, args);
	}

}
