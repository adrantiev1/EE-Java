package dmit2015.hr.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= {MaxSalaryValidator.class})
@Documented
public @interface ValidMaxSalary {

	String message() default "{dmit2015.hr.entity.Job.validMaxSalary.message}";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}
