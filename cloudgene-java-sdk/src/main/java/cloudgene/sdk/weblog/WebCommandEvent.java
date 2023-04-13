package cloudgene.sdk.weblog;

import java.io.IOException;

import cloudgene.sdk.internal.WorkflowContext;

public class WebCommandEvent {

	private WebCommand command;

	private Object[] params;

	public WebCommandEvent(WebCommand command, Object[] params) {
		super();
		this.command = command;
		this.params = params;
	}

	public WebCommand getCommand() {
		return command;
	}

	public void setCommand(WebCommand command) {
		this.command = command;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
	
	public void sendTo(Collector collector) throws Exception {
		collector.sendEvent(this);
	}
	
	public void execute(WorkflowContext context) throws IOException {
		WebCommandEventExecutor.execute(this, context);
	}

}
