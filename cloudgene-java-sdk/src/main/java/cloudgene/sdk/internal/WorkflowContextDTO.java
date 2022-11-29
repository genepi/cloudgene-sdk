package cloudgene.sdk.internal;

import java.util.Map;
import java.util.Set;

public class WorkflowContextDTO {

	private Map<String, String> params;

	private String jobName;

	private String jobId;

	private String workingDirectory;

	private Set<String> inputs;

	private Map<String, Integer> counters;

	private Map<String, Object> data;

	private String hdfsTemp;

	private String localTemp;

	private Map<String, String> config;

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public Set<String> getInputs() {
		return inputs;
	}

	public void setInputs(Set<String> inputs) {
		this.inputs = inputs;
	}

	public Map<String, Integer> getCounters() {
		return counters;
	}

	public void setCounters(Map<String, Integer> counters) {
		this.counters = counters;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getHdfsTemp() {
		return hdfsTemp;
	}

	public void setHdfsTemp(String hdfsTemp) {
		this.hdfsTemp = hdfsTemp;
	}

	public String getLocalTemp() {
		return localTemp;
	}

	public void setLocalTemp(String localTemp) {
		this.localTemp = localTemp;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

}
