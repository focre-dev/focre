package com.focre.adminrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.focre.*"})
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FocreAdminRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FocreAdminRestApplication.class, args);
	}
}
