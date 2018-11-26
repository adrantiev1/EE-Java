package dmit2015.hr.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;

import org.omnifaces.util.Messages;

import dmit2015.soap.HumanResourceService;
import dmit2015.soap.HumanResourceXmlWebService;

import java.io.Serializable;

@Named
@ViewScoped
public class LottoController implements Serializable {
	private static final long serialVersionUID = 1L;

	//@WebServiceRef(wsdlLocation="http://localhost:8080/dmit2015-fall2018term-assignment05-server-start/HumanResourceService?wsdl")
	private HumanResourceService hrEndPoint = new HumanResourceService();
	
	private HumanResourceXmlWebService hrService;
	
	
	@PostConstruct
	public void pageLoad() {
		hrService = hrEndPoint.getHumanResourcePort();
	}
	
	public void doLuckyNumber() {
		int luckyNumber = hrService.luckyLottoNumber();
		Messages.addGlobalInfo("Your lucky number is {0}", luckyNumber);
	}
}
