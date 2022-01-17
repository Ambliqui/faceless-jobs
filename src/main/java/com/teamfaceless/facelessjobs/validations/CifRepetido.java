package com.teamfaceless.facelessjobs.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

public class CifRepetido {
	@Documented
	@Constraint(validatedBy = CIFRepetidoValidator.class)
	@Target({ ElementType.METHOD, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CIFExist {
	    String message() default "El CIF ya existe en el sistema";

	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};
	}  
}
