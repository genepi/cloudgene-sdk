package cloudgene.sdk.weblog.collectors;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import cloudgene.sdk.internal.WorkflowContext;
import cloudgene.sdk.weblog.Collector;
import cloudgene.sdk.weblog.WebCommandEvent;

public class FileLog implements Collector {

	private DateFormat formatter = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

	private BufferedOutputStream logStream;

	public FileLog(String filename) throws FileNotFoundException {
		logStream = new BufferedOutputStream(new FileOutputStream(filename));
	}

	@Override
	public void sendEvent(WebCommandEvent event) throws Exception {

		switch (event.getCommand()) {
		case SUBMIT_COUNTER: {
			break;
		}
		case MESSAGE: {
			String message = (String) event.getParams()[0];
			int type = ((Integer) event.getParams()[1]);
			message(message, type);
			break;
		}
		case BEGIN_TASK: {
			String name = (String) event.getParams()[0];
			message(name, WorkflowContext.RUNNING);
			break;
		}
		case UPDATE_TASK: {
			// String name = (String) event.getParams()[0];
			// int type = ((Integer) event.getParams()[1]).intValue();
			// message(name, type);
			break;
		}
		case LOG: {
			String line = (String) event.getParams()[0];
			log(line);
			break;
		}
		case PRINTLN: {
			String line = (String) event.getParams()[0];
			info(line);
			break;
		}
		case END_TASK: {
			String name = (String) event.getParams()[0];
			int type = ((Integer) event.getParams()[1]).intValue();
			message(name, type);
			break;
		}
		case INC_COUNTER: {
			break;
		}
		default:

		}

	}

	public void info(String message) throws IOException {
		write("[INFO] " + message);
	}

	public void log(String message) throws IOException {
		write("[LOG] " + message);
	}

	public void message(String message, int type) throws IOException {

		switch (type) {
		case WorkflowContext.OK:
			write("[OK] " + message);
			break;
		case WorkflowContext.ERROR:
			write("[ERROR] " + message);
			break;
		case WorkflowContext.WARNING:
			write("[WARN] " + message);
			break;
		case WorkflowContext.RUNNING:
			write("[RUN] " + message);
			break;
		default:
			write("[INFO] " + message);
		}

	}

	public void write(String line) throws IOException {
		// logStream.write((formatter.format(new Date()) + " ").getBytes());
		logStream.write(line.getBytes("UTF-8"));
		logStream.write("\n".getBytes("UTF-8"));
		logStream.flush();

	}

	@Override
	public void close() throws IOException {
		logStream.close();
	}

}
