package cloudgene.sdk.weblog;

import java.io.FileNotFoundException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import cloudgene.sdk.internal.WorkflowContextDTO;
import cloudgene.sdk.internal.WorkflowContextUtil;

public class WebWorkflowContextUtil {

	public static WebWorkflowContext loadFromJson(String filename)
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {

		WorkflowContextDTO dto = WorkflowContextUtil.loadFromJson(filename);

		WebWorkflowContext context = new WebWorkflowContext();
		context.setJobId(dto.getJobId());
		context.setJobName(dto.getJobName());
		context.setConfig(dto.getConfig());
		context.setCounters(dto.getCounters());
		context.setHdfsTemp(dto.getHdfsTemp());
		context.setLocalTemp(dto.getLocalTemp());
		context.setWorkingDirectory(dto.getWorkingDirectory());
		context.setData(dto.getData());
		context.setParams(dto.getParams());
		context.setInputs(dto.getInputs());

		return context;

	}

}
