package dmit2015.rest.client;

import java.util.HashMap;
import java.util.Map;

public class Job {

private String jobId;
private String jobTitle;
private Integer maxSalary;
private Integer minSalary;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public String getJobId() {
return jobId;
}

public void setJobId(String jobId) {
this.jobId = jobId;
}

public String getJobTitle() {
return jobTitle;
}

public void setJobTitle(String jobTitle) {
this.jobTitle = jobTitle;
}

public Integer getMaxSalary() {
return maxSalary;
}

public void setMaxSalary(Integer maxSalary) {
this.maxSalary = maxSalary;
}

public Integer getMinSalary() {
return minSalary;
}

public void setMinSalary(Integer minSalary) {
this.minSalary = minSalary;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}
