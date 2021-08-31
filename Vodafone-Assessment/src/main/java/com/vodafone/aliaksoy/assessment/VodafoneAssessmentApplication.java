package com.vodafone.aliaksoy.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author aaksoy
 *
 */

@SpringBootApplication
@EnableSwagger2
public class VodafoneAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(VodafoneAssessmentApplication.class, args);
	}

}
