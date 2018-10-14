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
				"SELECT l FROM Location l ORDER BY l.country.countryName",Location.class
				).getResultList();
	}
	
//	Job CRUD
	public void addJob(Job newJob) {
		entityManager.persist(newJob);
	}
	public void updateJob(Job existingJob) {
		entityManager.merge(existingJob);
	}
	public void deleteJob(Job existingjob) {
		
		entityManager.remove( existingjob );
	}
	
	public Job findOneJob(String jobId) {
		return entityManager.find(Job.class, jobId);
	}
	
//	Location CRUD
	
	public void addLocation(Location newLocation) {
		entityManager.persist(newLocation);
	}
	
	public Location findOneLocation(long locationId) {
		return entityManager.find(Location.class, locationId);
	}
	
	public void updateLocation(Location existingLocation) {
		entityManager.merge(existingLocation);
	}
	public void deleteLocation(Location existingLocation) {
		
		entityManager.remove( existingLocation );
	}
	
}

