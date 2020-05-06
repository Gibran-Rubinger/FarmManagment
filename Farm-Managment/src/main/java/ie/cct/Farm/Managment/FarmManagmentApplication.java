package ie.cct.Farm.Managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

// this is allow us to scan inside this package controllers/components.
@ComponentScan("ie.cct.Farm.Managment*")
public class FarmManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmManagmentApplication.class, args);
	}

}
