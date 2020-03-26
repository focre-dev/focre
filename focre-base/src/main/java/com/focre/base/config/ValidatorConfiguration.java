package com.focre.base.config;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidatorConfiguration {

	public ResourceBundleMessageInterpolator getResourceBundleMessageInterpolator() {
		PlatformResourceBundleLocator locator = new PlatformResourceBundleLocator("i18n/ValidationMessages");
		ResourceBundleMessageInterpolator interpolator = new ResourceBundleMessageInterpolator(locator);
		return interpolator;
	}
	
	@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(@Qualifier("validator") Validator validator) {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator);  // 若不定制validator，此处可不用set
        return processor;
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .messageInterpolator(new MessageInterpolatorAdapter(getResourceBundleMessageInterpolator())) //
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
