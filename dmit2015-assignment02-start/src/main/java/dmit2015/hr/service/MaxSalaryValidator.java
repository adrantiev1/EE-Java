package dmit2015.hr.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dmit2015.hr.entity.Job;




public class MaxSalaryValidator implements ConstraintValidator<ValidMaxSalary, Job>{

	@Override
	public boolean isValid(Job currentJob, ConstraintValidatorContext context) {
		
		if (currentJob == null || currentJob.getMaxSalary() == null || currentJob.getMinSalary() == null) {
			return true;
		}
		
		return currentJob.getMaxSalary().doubleValue() > (currentJob.getMinSalary().doubleValue()* 1.25);
	}
}
