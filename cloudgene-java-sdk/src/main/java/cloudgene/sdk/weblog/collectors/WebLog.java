package cloudgene.sdk.weblog.collectors;

import cloudgene.sdk.weblog.Collector;
import cloudgene.sdk.weblog.WebCommandEvent;
import cloudgene.sdk.weblog.WebCommandEventUtil;

public class WebLog implements Collector {

	private String url;
	
	public WebLog(String url) {
		this.url = url;
	}
	
	@Override
	public void sendEvent(WebCommandEvent event) throws Exception {
		WebCommandEventUtil.send(event, url);
	}

	@Override
	public void close() {
		
	}

}
