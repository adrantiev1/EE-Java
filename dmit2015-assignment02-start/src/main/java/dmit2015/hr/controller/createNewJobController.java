package dmit2015.hr.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.hr.entity.Job;
import dmit2015.hr.service.HumanResourceService;



@Named
@ViewScoped
public class createNewJobController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private HumanResourceService currentHumanResourceService;
	
	@Produces
	@Named
	private Job newJob;
	
	@PostConstruct
	public void initNewJob() {
		newJob = new Job();
	}
	
	public void createNewJOb() {
		try {
			currentHumanResourceService.addJob(newJob);
			initNewJob();
			Messages.addGlobalInfo("Add successful");
		} catch (Exception e) {
			Messages.addGlobalError("Add unsuccessful");
			Messages.addGlobalError("{0}", e.getMessage());	
		}
	}
	
	public String cancel() {
		newJob= null;
		String nextPage = "jobDetail?faces-redirect=true";
		return nextPage;
	}
}
