package dmit2015.hr.service;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.commons.lang3.ArrayUtils;

import dmit2015.hr.entity.Job;
import northwind.entity.Shipper;


@WebService(serviceName="HumanResourceService",
			targetNamespace="http://localhost/",
			portName="HumanResourcePort")
public class HumanResourceXmlWebService {

	@Inject
	private HumanResourceService HumanResourceService;
	public int luckyLottoNumber() {
		return new Random().nextInt(49)+1;
	}
	
	public String luckyNumberArray(int amount) {
		int[] numberArray = new int[amount];
		int rndNumber = new Random().nextInt(49)+1;
		for (int i = 0; i < amount; i++) {
			do {
				rndNumber = new Random().nextInt(49)+1;
			} while (ArrayUtils.contains(numberArray, rndNumber));
			
			numberArray[i] = rndNumber;
		}
		java.util.Arrays.sort(numberArray);
		String numString = "";
		for (int num : numberArray) {
			numString += num + " ";
		}
		
		return numString;
		
	}
	public List<Job> findAllJob() {
		return HumanResourceService.findAllJobs();		
	}
	
	public @WebResult(name="Job") Job findOneJob(@WebParam(name="JobId") String JobId) {
		return HumanResourceService.findOneJob(JobId);
	}
	
	
}
