package cloudgene.sdk.internal;

import java.util.Map;
import java.util.Set;

public abstract class WorkflowContext {

	public static final int OK = 0;

	public static final int ERROR = 1;

	public static final int WARNING = 2;

	public static final int RUNNING = 3;

	public abstract String getInput(String param);

	public abstract String getJobId();

	public abstract String getOutput(String param);

	public abstract String get(String param);

	public abstract void println(String line);

	public abstract void log(String line);

	public abstract String getWorkingDirectory();

	public abstract boolean sendNotification(String body) throws Exception;

	public abstract boolean sendMail(String subject, String body) throws Exception;

	public abstract boolean sendMail(String to, String subject, String body) throws Exception;

	public abstract Set<String> getInputs();

	public abstract void setInput(String input, String value);

	public abstract void setOutput(String input, String value);

	public abstract void incCounter(String name, int value);

	public abstract void submitCounter(String name);

	public abstract Map<String, Integer> getCounters();

	public abstract Object getData(String key);

	public abstract String createLinkToFile(String id);

	public String createLinkToFile(String id, String filename) {
		return "[NOT AVAILABLE]";

	}

	public abstract String getJobName();

	public abstract String getHdfsTemp();

	public abstract String getLocalTemp();

	public abstract void setConfig(Map<String, String> config);

	public abstract String getConfig(String param);

	public abstract void message(String message, int type);
	
	private IExternalWorkspace externalWorkspace;
	
	public void addDownload(String param, String name, String size, String path) {
		
	}
	
	public void setExternalWorkspace(IExternalWorkspace externalWorkspace) {
		this.externalWorkspace = externalWorkspace;
	}
	
	public IExternalWorkspace getExternalWorkspace() {
		return externalWorkspace;
	}
	
	public void ok(String message) {
		message(message, OK);
	}

	public void error(String message) {
		message(message, ERROR);
	}

	public void warning(String message) {
		message(message, WARNING);
	}

	public abstract void beginTask(String name);

	public abstract void updateTask(String name, int type);

	public abstract void endTask(String message, int type);

	public void endTask(String message, Exception e){
		//TODO: add trace
		String messageWithTrace = message + ". " + e.toString();
		endTask(messageWithTrace, ERROR);
	}
	
}
