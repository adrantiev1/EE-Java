package dmit2015.hr.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.hr.entity.Location;
import dmit2015.hr.service.HumanResourceService;

@Named
@ViewScoped
public class createLocationController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private HumanResourceService currentHumanResourceService;
	
	@Produces
	@Named
	private Location newLocation;
	
	@PostConstruct
	public void initLocation() {
		newLocation = new Location();
		}
	
	public void createNewLocation() {
		try {
			currentHumanResourceService.addLocation(newLocation);
			initLocation();
			Messages.addGlobalInfo("Add successful");
			
		} catch (Exception e) {
			Messages.addGlobalError("Add unsuccessful");
		}
	}

}
