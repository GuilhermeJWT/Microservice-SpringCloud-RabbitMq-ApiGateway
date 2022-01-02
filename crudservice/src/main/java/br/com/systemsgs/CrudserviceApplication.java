package br.com.systemsgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CrudserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudserviceApplication.class, args);
	}

}
