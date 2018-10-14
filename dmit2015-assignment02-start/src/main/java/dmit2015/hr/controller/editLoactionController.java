package dmit2015.hr.controller;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	

	private String idQueryValue;
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
	
	public void updateLocation() {
		try {if (!countryIdSelected.isEmpty() && countryIdSelected != null) {
			Country selectedCountry = currentHumanResourceService.findOneCountry(countryIdSelected);
			existingLocation.setCountry(selectedCountry);
		}
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
			countryIdSelected="";
			Messages.addGlobalInfo("Delete successful");
		} catch (Exception e) {
			Messages.addGlobalError("Delete unsuccessful {0}" , e);			
		}
	}
	
	public void cancel() {
		existingLocation = null;
		idQueryValue = null;
	}
	
	
	
	
	
	
}
