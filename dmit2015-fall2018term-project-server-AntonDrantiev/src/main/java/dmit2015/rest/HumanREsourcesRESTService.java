package dmit2015.rest;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dmit2015.hr.entity.Job;
import dmit2015.hr.service.HumanResourceService;

@Path("humanResources-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HumanREsourcesRESTService {
	
	@Inject
	private HumanResourceService hrService;
	
	@Path("Jobs")
	@POST
	public void CreateJob(Job newJob) {
		hrService.addJob(newJob);
	}
	@Path("Jobs/{id}")
	@GET
	public Job FindOneJob(@PathParam("id") String jobId) {
		return hrService.findOneJob(jobId);
	}
	@Path("Jobs")
	@GET
	public List<Job> FindAllJobs() {
		return hrService.findAllJobs();		
	}
	@Path("Jobs")
	@PUT
	public void UpdateJob(Job existingJob) {
		hrService.updateJob(existingJob);
		
	}
	@Path("Jobs/{id}")
	@DELETE
	public void DeleteJob(@PathParam("id") String jobId) {
		Job existingJob = hrService.findOneJob(jobId);
		try {
			hrService.deleteJob(existingJob);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
