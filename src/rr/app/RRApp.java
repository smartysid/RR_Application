package rr.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RRApp extends ResourceConfig 
{
	
	public void ResourceConfig()
	{
		packages("rr.app");
	}
	
	

}
