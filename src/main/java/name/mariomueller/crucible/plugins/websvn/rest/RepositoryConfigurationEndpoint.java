/**
 * Copyright 2010 Mario Mueller <mario.mueller.mac@me.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
