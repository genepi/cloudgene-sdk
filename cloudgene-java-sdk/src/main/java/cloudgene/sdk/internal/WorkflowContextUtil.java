package cloudgene.sdk.internal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class WorkflowContextUtil {

	public static WorkflowContextDTO loadFromJson(String filename)
			throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = (new GsonBuilder()).create();
		return gson.fromJson(new FileReader(filename), WorkflowContextDTO.class);
	}

	public static void writeToJson(WorkflowContextDTO context, String filename) throws JsonIOException, IOException {
		Gson gson = (new GsonBuilder()).create();
		FileWriter fileWriter = new FileWriter(filename);
		gson.toJson(context, fileWriter);
		fileWriter.close();
	}

}
