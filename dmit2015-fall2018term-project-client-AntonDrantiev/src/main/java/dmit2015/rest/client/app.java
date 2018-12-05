package dmit2015.rest.client;

import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;



public class app {
	
	final static String BASE_URI = "http://localhost:8080/dmit2015-fall2018term-project-server-start/rest/humanResources-api";
	public static void main(String[] args) {
		Client restClient = ClientBuilder.newClient();
		Response response = restClient.target(BASE_URI).request().get();
		
		
		Job newJob = new Job();
		newJob.setJobId("Tset");
		newJob.setJobTitle("test");
		response = restClient.target(BASE_URI).request().post(Entity.json(newJob));
		if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			
		}
		
		
		
		if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			List<Job> jobs = response.readEntity(new GenericType<List<Job>>() {});
			jobs.forEach(singJob -> System.out.println(singJob.getJobId()+ "," + singJob.getJobTitle()+ "," +singJob.getMinSalary()+ "," + singJob.getMaxSalary()));
		}else {
			JOptionPane.showMessageDialog(null, "Server status" + response.getStatus(),"Server Response", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		
		
	}

}
