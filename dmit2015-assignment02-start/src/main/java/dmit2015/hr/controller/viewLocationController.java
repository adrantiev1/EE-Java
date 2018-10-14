package dmit2015.hr.controller;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dmit2015.hr.entity.Country;
import dmit2015.hr.entity.Location;
import dmit2015.hr.service.HumanResourceService;

@Named
@ViewScoped
public class viewLocationController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private HumanResourceService currentHRService;
	
	private List<Location> locations;	// +getter
	private List<Country> countries;	// +getter
	
	
	public List<Location> getLocations() {
		return locations;
	}
	public List<Country> getCountries() {
		return countries;
	}
	
	@PostConstruct
	public void init() {
		locations = currentHRService.findAllLocation();
		countries = currentHRService.findAllCountries();
	}


}
