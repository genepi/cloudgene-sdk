package cloudgene.sdk.weblog;

import java.io.FileNotFoundException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import cloudgene.sdk.internal.WorkflowStep;

public class WebLogRunner {
	
	public static String VERSION = "1.0.2";

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, JsonSyntaxException, JsonIOException, FileNotFoundException {

		WebCommandEventUtil.DEBUG = false;
		
		String step = args[0];
		String json = args[1];
		String collector = args[2];

		WebWorkflowContext context = WebWorkflowContextUtil.loadFromJson(json);
		context.setCollectorUrl(collector);

		Class myClass = WebLogRunner.class.getClassLoader().loadClass(step);
		Object object = myClass.newInstance();

		if (object instanceof WorkflowStep) {
			WorkflowStep workflowStep = (WorkflowStep) object;
			workflowStep.setup(context);

			if (workflowStep.run(context)) {
				System.exit(0);
			} else {
				System.exit(1);
			}
		} else {
			System.err.println("Class " + step + " is not a WorkflowStep.");
			System.exit(1);
		}

		// Implement SIG KILL listener

	}

}
