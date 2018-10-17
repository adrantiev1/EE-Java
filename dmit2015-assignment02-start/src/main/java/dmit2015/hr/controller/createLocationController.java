package dmit2015.hr.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import org.omnifaces.util.Messages;

import dmit2015.hr.entity.Country;
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
	
	@NotBlank(message="A Country must be selected")
	private String countryIdSelected;
	
	public String getCountryIdSelected() {
		return countryIdSelected;
	}

	public void setCountryIdSelected(String countryIdSelected) {
		this.countryIdSelected = countryIdSelected;
	}

	
	public void createNewLocation() {
		try {
			if(countryIdSelected != null) {
				Country selectedCountry = currentHumanResourceService.findOneCountry(countryIdSelected);
				newLocation.setCountry(selectedCountry);
			}
			currentHumanResourceService.addLocation(newLocation);
			initLocation();
			Messages.addGlobalInfo("Add successful");
			
		} catch (Exception e) {
			Messages.addGlobalError("Add unsuccessful");
			Messages.addGlobalError("{0}", e.getMessage());	
		}
	}
	
	public String cancel() {
		
		
		newLocation = null;
		countryIdSelected = null;
			
		String nextPage = "locationDetail?faces-redirect=true";
		return nextPage;
		
	}

	
}
