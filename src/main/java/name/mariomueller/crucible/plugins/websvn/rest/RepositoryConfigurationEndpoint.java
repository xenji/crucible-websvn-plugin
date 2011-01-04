package name.mariomueller.crucible.plugins.websvn.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import name.mariomueller.crucible.plugins.websvn.data.RepositoryConfigurationWrapper;
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
@Path("/repository")
public class RepositoryConfigurationEndpoint {

	private ConfigurationStoreService storageService;

	public ConfigurationStoreService getStorageService() {
		return storageService;
	}

	public void setStorageService(ConfigurationStoreService storageService) {
		this.storageService = storageService;
	}

	@GET
	@Path("/{repositoryKey}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getRepositoryConfigurationResource(@PathParam("repositoryKey") String repositoryKey) {
		RepositoryConfigurationWrapper repositoryConfig = getStorageService().getConfigForRepository(repositoryKey);
		return Response.ok(repositoryConfig).build();
	}

	@PUT
	@Path("/{repositoryKey}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response setRepositoryConfigurationResource(@PathParam("repositoryKey") String repositoryKey,
													   JAXBElement<RepositoryConfigurationWrapper> repositoryConfig) {
		getStorageService().store(repositoryConfig.getValue());
		return Response.ok(repositoryConfig.getValue()).build();
	}
}
