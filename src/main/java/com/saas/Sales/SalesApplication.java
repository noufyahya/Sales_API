package com.saas.Sales;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.saas.Sales.service.salesService;


import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootApplication
public class SalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
		@Bean
		CommandLineRunner runner(salesService saleSer) {
			return args -> {
				// Path to your CSV
				try (InputStream is = new FileInputStream("src/main/resources/sales.csv")) {
					saleSer.importSalesCsv(is);
					System.out.println(">>> CSV Import Success! Check pgAdmin now. <<<");
				} catch (Exception e) {
					System.out.println(">>> Import failed. Is the file path correct? <<<");
				}
			};
		}


}
