package cloudgene.sdk.weblog;

public interface Collector {

	public void sendEvent(WebCommandEvent event) throws Exception;

	public void close() throws Exception;

}
