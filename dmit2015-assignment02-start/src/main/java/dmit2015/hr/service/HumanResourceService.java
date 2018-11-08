package dmit2015.hr.service;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import dmit2015.hr.entity.Country;
import dmit2015.hr.entity.Job;
import dmit2015.hr.entity.Location;


@Stateless
@DeclareRoles({"DMIT2015Instructor","DMIT2015Student","PROG_DMIT"})
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
	@RolesAllowed({"DMIT2015Instructor","DMIT2015Student","PROG_DMIT"})
	public void addJob(Job newJob) {
		entityManager.persist(newJob);
	}
	@RolesAllowed({"DMIT2015Instructor","DMIT2015Student"})
	public void updateJob(Job existingJob) {
		entityManager.merge(existingJob);
	}
	@RolesAllowed({"DMIT2015Instructor"})
	public void deleteJob(Job existingjob) throws Exception {
		existingjob = entityManager.merge(existingjob);
		if (existingjob.getEmployees().size() > 0  || existingjob.getJobHistories().size() > 0 ) {
			throw new Exception("You are not allowed to delete a job with existing employees or histories.");
		}
		entityManager.remove( existingjob );
	}
	public void deleteJobById(String jobId) throws Exception {
		Job existingJob = findOneJob(jobId);
		deleteJob(existingJob);
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
	public void deleteLocation(Location existingLocation) throws Exception{
		existingLocation = entityManager.merge(existingLocation);
		if (existingLocation.getDepartments().size() > 0) {
			throw new Exception("You are not allowed to delete a location with existing departments.");
		}
		entityManager.remove( existingLocation );
	}
	public void deleteLocationById(Long locationId) throws Exception {
		Location existingLocation = findOneLocation(locationId);
		deleteLocation(existingLocation);
	}
	
	
//	for finding the country name and id
	public List<Country> findAllCountries(){
		return entityManager.createQuery(
				"SELECT c FROM Country c ORDER BY c.countryName",Country.class
				).getResultList();
	}
	
	public Country findOneCountry(String countryId) {
		return entityManager.find(Country.class, countryId);
	}
	
}

