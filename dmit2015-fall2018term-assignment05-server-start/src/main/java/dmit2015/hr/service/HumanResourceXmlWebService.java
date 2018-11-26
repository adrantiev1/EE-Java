package dmit2015.hr.service;

import java.util.Random;

import javax.jws.WebService;

@WebService(serviceName="HumanResourceService",
			targetNamespace="http://localhost/",
			portName="HumanResourcePort")
public class HumanResourceXmlWebService {

	public int luckyLottoNumber() {
		return new Random().nextInt(49)+1;
	}
}
