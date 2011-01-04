package name.mariomueller.crucible.plugins.websvn.rest;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * User: mario
 * Date: 04.01.11
 * Time: 20:28
 */
@Path("/repository")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class RepositoryConfigurationEndpoint {

	private PluginSettingsFactory settingsFactory;

	public PluginSettingsFactory getSettingsFactory() {
		return settingsFactory;
	}

	public void setSettingsFactory(PluginSettingsFactory settingsFactory) {
		this.settingsFactory = settingsFactory;
	}

}
