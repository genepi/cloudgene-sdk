package cloudgene.sdk.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IExternalWorkspace {

	public void setup(String job) throws IOException;

	public String upload(String id, File file) throws IOException;

	public InputStream download(String url) throws IOException;

	public void delete(String job) throws IOException;

	public String getName();

	public String createPublicLink(String url);

}
