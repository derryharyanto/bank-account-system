package com.asklora.system.bankaccountsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.asklora.system.bankmodelorm","com.asklora.system.bankaccountsystem"})
public class BankAccountSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountSystemApplication.class, args);
	}

}
