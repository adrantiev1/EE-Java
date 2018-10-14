package dmit2015.hr.controller;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.hr.entity.Location;
import dmit2015.hr.service.HumanResourceService;


@Named
@ViewScoped
public class editLoactionController implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private HumanResourceService currentHumanResourceService;
	
	@Produces
	@Named
	private Location existingLocation;
	private String idQueryValue;
	
	public String getIdQueryValue() {
		return idQueryValue;
	}
	public void setIdQueryValue(String idQueryValue) {
		this.idQueryValue = idQueryValue;
	}
	
	public void findLocation() {
		try {
			existingLocation = currentHumanResourceService.findOneLocation(Long.valueOf(idQueryValue));
			if(existingLocation != null) {
				Messages.addGlobalInfo("Query successful");
			}else {
				Messages.addGlobalError("Query unsuccessful");
			}
		} catch (Exception e) {
			Messages.addGlobalError("Query unsucessful");
			
		}
	}
	
	public void updateLocation() {
		try {
			currentHumanResourceService.updateLocation(existingLocation);;
			Messages.addGlobalInfo("Update successful");
		} catch (Exception e) {
			Messages.addGlobalError("Update unsuccessful");			
		}
	}
	
	public void deleteLoaction() {
		try {
			currentHumanResourceService.deleteLocation(existingLocation);;
			existingLocation = null;
			idQueryValue = null;
			Messages.addGlobalInfo("Delete successful");
		} catch (Exception e) {
			Messages.addGlobalError("Delete unsuccessful");			
		}
	}
	
	public void cancel() {
		existingLocation = null;
		idQueryValue = null;
	}
	
	
	
	
	
	
}
