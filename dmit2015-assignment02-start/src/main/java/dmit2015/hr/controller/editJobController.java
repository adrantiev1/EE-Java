package dmit2015.hr.controller;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.hr.entity.Job;
import dmit2015.hr.service.HumanResourceService;

@Named
@ViewScoped
public class editJobController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private HumanResourceService currentHumanResourceService;
	
	@Produces
	@Named
	private Job existingJob;
	public Job getExistingJob() {
		return existingJob;
	}
	

	private String idQueryValue;		// +getter +setter
	
	public String getIdQueryValue() {
		return idQueryValue;
	}
	public void setIdQueryValue(String idQueryValue) {
		this.idQueryValue = idQueryValue;
	}
	
	public void findJob() {
		try {
			existingJob = currentHumanResourceService.findOneJob(idQueryValue);
			if (existingJob != null) {
				Messages.addGlobalInfo("Query successful");
				
			} else {
				Messages.addGlobalError("Query unsuccessful");
				
			}
		} catch (Exception e) {
			Messages.addGlobalError("Query unsucessful {0}", e);			
		}
	}
	public void updateJob() {
		try {
			currentHumanResourceService.updateJob(existingJob);;
			Messages.addGlobalInfo("Update successful");
		} catch (Exception e) {
			Messages.addGlobalError("Update unsuccessful");			
		}
	}
	
	public void deleteJob() {
		try {
			currentHumanResourceService.deleteJob(existingJob);;
			existingJob = null;
			idQueryValue = null;
			Messages.addGlobalInfo("Delete successful");
		} catch (Exception e) {
			Messages.addGlobalError("Delete unsuccessful");			
		}
	}
	
	public void cancel() {
		existingJob = null;
		idQueryValue = null;
	}
	
	
	

}
