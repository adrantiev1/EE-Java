package dmit2015.hr.controller;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import dmit2015.hr.entity.Country;
import dmit2015.hr.entity.Location;
import dmit2015.hr.service.HumanResourceService;


@Named
@ViewScoped
public class editLoactionController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private HumanResourceService currentHumanResourceService;
	
	@Produces
	@Named
	private Location existingLocation;
	public Location getExistingLocation() {
		return existingLocation;
	}
	
	@NotNull(message="Search value is required.")
	private String idQueryValue;
	
	@NotBlank(message="A Country must be selected")
	private String countryIdSelected;
	
	public String getCountryIdSelected() {
		return countryIdSelected;
	}
	public void setCountryIdSelected(String countryIdSelected) {
		this.countryIdSelected = countryIdSelected;
	}
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
				countryIdSelected = existingLocation.getCountry().getCountryId();				
				Messages.addGlobalInfo("Query successful");
			}else {
				Messages.addGlobalError("Query unsuccessful");
			}
		} catch (Exception e) {
			Messages.addGlobalError("Query unsucessful: {0}",e);
			
		}
	}
	
	public String updateLocation() {
		String nextPage = null;
		try {if (!countryIdSelected.isEmpty() && countryIdSelected != null) {
			Country selectedCountry = currentHumanResourceService.findOneCountry(countryIdSelected);
			existingLocation.setCountry(selectedCountry);
		}
			currentHumanResourceService.updateLocation(existingLocation);;
			Messages.addGlobalInfo("Update successful");
			nextPage = "viewLocations?faces-redirect=true";
		} catch (Exception e) {
			Messages.addGlobalError("Update unsuccessful");			
		}
		return nextPage;
	}
	
	public String deleteLoaction() {
		String nextPage = null;
		try {
			currentHumanResourceService.deleteLocation(existingLocation);;
			existingLocation = null;
			idQueryValue = null;
			countryIdSelected="";
			Messages.addGlobalInfo("Delete successful");
			nextPage = "viewLocations?faces-redirect=true";
		} catch (Exception e) {
			Messages.addGlobalError("Delete unsuccessful {0}" , e);			
		}
		return nextPage;
	}
	
	public void cancel() {
		existingLocation = null;
		idQueryValue = null;
	}
	public void findByQueryParameterId() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (idQueryValue != null && existingLocation == null) {
				findLocation();		
			}
		}
	}
	
	
	
	
	
	
}
