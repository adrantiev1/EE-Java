package dmit2015.hr.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import dmit2015.hr.entity.Job;
import dmit2015.hr.entity.Location;

@Stateless
public class HumanResourceService {
	
	@Inject
	private EntityManager entityManager;
	
	public List<Job> findAllJob(){
		return entityManager.createQuery(
				"SELECT j FROM Job j ORDER BY j.jobTitle",Job.class
				).getResultList();
	}
	
	public List<Location> findAllLocation(){
		return entityManager.createQuery(
				"SELECT l FROM Location l",Location.class
				).getResultList();
	}

}
