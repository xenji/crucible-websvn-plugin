package name.mariomueller.crucible.plugins.websvn.rest;

import name.mariomueller.crucible.plugins.websvn.data.GlobalConfigurationWrapper;
import name.mariomueller.crucible.plugins.websvn.services.ConfigurationStoreService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

/**
 * User: mario
 * Date: 04.01.11
 * Time: 20:28
 */
@Path("/")
public class GlobalConfigurationEndpoint {
	private ConfigurationStoreService storageService;

	public ConfigurationStoreService getStorageService() {
		return storageService;
	}

	public void setStorageService(ConfigurationStoreService storageService) {
		this.storageService = storageService;
	}

	@GET
	@Path("/global")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getGlobalConfigurationResource() {
		GlobalConfigurationWrapper globalConfig = getStorageService().getGlobalConfig();
		return Response.ok(globalConfig).build();
	}

	@PUT
	@Path("/global")
	@Consumes(MediaType.APPLICATION_XML)
	public Response setGlobalConfigurationResource(JAXBElement<GlobalConfigurationWrapper> globalConfig) {
		getStorageService().store(globalConfig.getValue());
		return Response.ok(globalConfig.getValue()).build();
	}
}
