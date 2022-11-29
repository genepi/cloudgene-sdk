package cloudgene.sdk.weblog;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WebCommandEventUtil {

	public static boolean DEBUG = false;

	public static boolean DRY = false;

	public static WebCommandEvent parse(String json) {

		Gson gson = (new GsonBuilder()).create();
		WebCommandEvent event = gson.fromJson(json, WebCommandEvent.class);
		return event;

	}

	public static void send(WebCommandEvent event, String url) throws IOException, InterruptedException {

		Gson gson = (new GsonBuilder()).create();
		String json = gson.toJson(event);

		if (DEBUG) {
			System.out.println("Send " + json + " to " + url);
		}

		if (DRY) {
			return;
		}

		String response = post(url, json);

		if (DEBUG) {
			System.out.println(response);
		}

	}

	protected static String post(String url, String body) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
				.POST(HttpRequest.BodyPublishers.ofString(body)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}

}
