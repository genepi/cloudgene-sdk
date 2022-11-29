package cloudgene.sdk.weblog;

import java.util.Map;
import java.util.Set;

import cloudgene.sdk.internal.WorkflowContext;

public class WebWorkflowContext extends WorkflowContext {

	private String jobId;

	private String jobName;

	private String hdfsTemp;

	private String localTemp;

	private String workingDirectory;

	private String collectorUrl;

	private Map<String, String> params;

	private Map<String, Object> data;

	private Map<String, String> config;
	
	private Set<String> inputs;

	private Map<String, Integer> counters;

	public WebWorkflowContext() {

	}

	public void setCollectorUrl(String collectorUrl) {
		this.collectorUrl = collectorUrl;
	}

	@Override
	public String getInput(String param) {
		return params.get(param);
	}

	@Override
	public String getJobId() {
		return jobId;
	}

	@Override
	public String getOutput(String param) {
		return params.get(param);
	}

	@Override
	public String get(String param) {
		return params.get(param);
	}

	@Override
	public void println(String line) {
		sendCommand(WebCommand.PRINTLN, line);
	}

	@Override
	public void log(String line) {
		sendCommand(WebCommand.LOG, line);
	}

	@Override
	public String getWorkingDirectory() {
		return workingDirectory;
	}

	@Override
	public boolean sendMail(String subject, String body) throws Exception {
		sendCommand(WebCommand.SEND_MAIL, subject, body);
		return true;
	}

	@Override
	public boolean sendMail(String to, String subject, String body) throws Exception {
		sendCommand(WebCommand.SEND_MAIL_TO, to, subject, body);
		return true;
	}

	@Override
	public boolean sendNotification(String body) throws Exception {
		sendCommand(WebCommand.SEND_NOTIFICATION, body);
		return true;
	}

	@Override
	public Set<String> getInputs() {
		return inputs;
	}

	public void setInputs(Set<String> inputs) {
		this.inputs = inputs;
	}
	
	@Override
	public void setInput(String input, String value) {
		sendCommand(WebCommand.SET_INPUT, input, value);
		params.put(input, value);
	}

	@Override
	public void setOutput(String output, String value) {
		sendCommand(WebCommand.SET_OUTPUT, output, value);
		params.put(output, value);
	}

	@Override
	public void incCounter(String name, int value) {
		sendCommand(WebCommand.INC_COUNTER, name, value);
	}

	@Override
	public void submitCounter(String name) {
		sendCommand(WebCommand.SUBMIT_COUNTER, name);
	}

	@Override
	public Map<String, Integer> getCounters() {
		return counters;
	}

	public void setCounters(Map<String, Integer> counters) {
		this.counters = counters;
	}
	
	@Override
	public Object getData(String key) {
		return data.get(key);
	}

	@Override
	public String createLinkToFile(String id) {
		// TODO Auto-generated method stub
		return "[NOT AVAILABLE]";
	}

	@Override
	public String createLinkToFile(String id, String filename) {
		// TODO Auto-generated method stub
		return "[NOT AVAILABLE]";
	}

	@Override
	public String getJobName() {
		return jobName;
	}

	@Override
	public String getHdfsTemp() {
		return hdfsTemp;
	}

	@Override
	public String getLocalTemp() {
		return localTemp;
	}

	@Override
	public void message(String message, int type) {
		sendCommand(WebCommand.MESSAGE, message, type);
	}

	@Override
	public void beginTask(String name) {
		sendCommand(WebCommand.BEGIN_TASK, name);
	}

	@Override
	public void updateTask(String name, int type) {
		sendCommand(WebCommand.UPDATE_TASK, name, type);
	}

	@Override
	public void endTask(String message, int type) {
		sendCommand(WebCommand.END_TASK, message, type);
	}

	@Override
	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

	@Override
	public String getConfig(String param) {
		if (config != null) {
			return config.get(param);
		} else {
			return null;
		}
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public void sendCommand(WebCommand command, Object... params) {
		if (collectorUrl == null) {
			return;
		}
		WebCommandEvent event = new WebCommandEvent(command, params);
		try {
			event.sendTo(collectorUrl);
		} catch (Exception e) {
			System.err.println("Send event to weblog failed." + e.toString());
		}
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setHdfsTemp(String hdfsTemp) {
		this.hdfsTemp = hdfsTemp;
	}

	public void setLocalTemp(String localTemp) {
		this.localTemp = localTemp;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
