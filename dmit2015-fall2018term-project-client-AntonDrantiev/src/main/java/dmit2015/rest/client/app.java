package dmit2015.rest.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;

import org.apache.commons.io.output.TaggedOutputStream;



public class app {
	
	final static String BASE_URI = "http://localhost:8080/dmit2015-fall2018term-project-server-start/rest/humanResources-api/Jobs";
	public static void main(String[] args) throws Exception {
		
		
		listAllJobs();
		System.out.println("");
		createJob();
		System.out.println("");
		findOneJob();
		System.out.println("");
		System.out.println("");
		updateJob();
		System.out.println("");
		listAllJobs();
		System.out.println("");
		deletejob();
		System.out.println("");
		listAllJobs();
		
	
	}
	public static void deletejob() throws IOException {
		Client restClient = ClientBuilder.newClient();
		Response response = restClient.target(BASE_URI).request().get();
		//find one job
		System.out.println("Delete a job");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyInput = new BufferedReader(in);
				System.out.println("enter job id ");
				String finJobById = keyInput.readLine();
				
				response = restClient.target(BASE_URI).path(finJobById).request().delete();		
				if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
					System.out.println("");
					System.out.println("Job deleted");
					
				}else {
					JOptionPane.showMessageDialog(null, "Server status" + response.getStatus(),"Server Response", JOptionPane.ERROR_MESSAGE);
				}
	}
	public static void listAllJobs() {
		Client restClient = ClientBuilder.newClient();
		Response response = restClient.target(BASE_URI).request().get();
		
		//List all jobs
		if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			List<Job> jobs = response.readEntity(new GenericType<List<Job>>() {});
			jobs.forEach(singJob -> System.out.println(singJob.getJobId()+ "," + singJob.getJobTitle()+ "," +singJob.getMinSalary()+ "," + singJob.getMaxSalary()));
		}else {
			JOptionPane.showMessageDialog(null, "Server status" + response.getStatus(),"Server Response", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	public static void findOneJob() throws IOException {
		Client restClient = ClientBuilder.newClient();
		Response response = restClient.target(BASE_URI).request().get();
		//find one job
		System.out.println("find one job");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyInput = new BufferedReader(in);
				System.out.println("enter job id ");
				String finJobById = keyInput.readLine();
				
				response = restClient.target(BASE_URI).path(finJobById).request().get();		
				if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
					Job foundJob = response.readEntity(Job.class);
					System.out.println("Job found =)");
					System.out.println("");
					System.out.println(foundJob.getJobId()+ "," + foundJob.getJobTitle()+ "," +foundJob.getMinSalary()+ "," + foundJob.getMaxSalary());
				}else {
					JOptionPane.showMessageDialog(null, "Server status" + response.getStatus(),"Server Response", JOptionPane.ERROR_MESSAGE);
				}
				
	}
	public static void createJob() throws IOException {
		
		Client restClient = ClientBuilder.newClient();
		Response response = restClient.target(BASE_URI).request().get();
		//create job
		System.out.println("");
				System.out.println("create job");
				InputStreamReader in = new InputStreamReader(System.in);
				BufferedReader keyInput = new BufferedReader(in);
				
				System.out.println("enter job id");
				String id = keyInput.readLine();
				System.out.println("enter job title");
				String title = keyInput.readLine();
				System.out.println("enter job min salary");
				String minSal = keyInput.readLine();
				System.out.println("enter job max salary");
				String maxSal = keyInput.readLine();
				
				Job newJob = new Job();
				newJob.setJobId(id);
				newJob.setJobTitle(title);
				newJob.setMinSalary(Integer.parseInt(minSal) );
				newJob.setMaxSalary(Integer.parseInt(maxSal) );
				response = restClient.target(BASE_URI).request().post(Entity.json(newJob));
				if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
					System.out.println("Job created");
				}else {
					JOptionPane.showMessageDialog(null, "Server status" + response.getStatus(),"Server Response", JOptionPane.ERROR_MESSAGE);
				}
	}
	public static void updateJob() throws IOException {
		//upadte
		Client restClient = ClientBuilder.newClient();
		Response response = restClient.target(BASE_URI).request().get();
		
		System.out.println("upadte job");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyInput = new BufferedReader(in);
		
				
				System.out.println("enter job id");
				String id = keyInput.readLine();
				System.out.println("enter job title");
				String title = keyInput.readLine();
				System.out.println("enter job min salary");
				String minSal = keyInput.readLine();
				System.out.println("enter job max salary");
				String maxSal = keyInput.readLine();
				
				Job newJob = new Job();
				newJob.setJobId(id);
				newJob.setJobTitle(title);
				newJob.setMinSalary(Integer.parseInt(minSal) );
				newJob.setMaxSalary(Integer.parseInt(maxSal) );
				response = restClient.target(BASE_URI).request().put(Entity.json(newJob));
				if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
					System.out.println("Job created");
				}else {
					JOptionPane.showMessageDialog(null, "Server status" + response.getStatus(),"Server Response", JOptionPane.ERROR_MESSAGE);
				}
	}

}
