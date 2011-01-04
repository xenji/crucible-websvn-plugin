package name.mariomueller.crucible.plugins.websvn.services;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import name.mariomueller.crucible.plugins.websvn.data.GlobalConfigurationWrapper;
import name.mariomueller.crucible.plugins.websvn.data.RepositoryConfigurationWrapper;

/**
 * User: mario
 * Date: 04.01.11
 * Time: 20:38
 */
public class ConfigurationStoreService {

	private PluginSettingsFactory settingsFactory;

	private PluginSettings globalSettings;

	private PluginSettings getGlobalSettings() {
		if (globalSettings == null) {
			setGlobalSettings(settingsFactory.createGlobalSettings());
		}
		return globalSettings;
	}

	private void setGlobalSettings(PluginSettings globalSettings) {
		this.globalSettings = globalSettings;
	}

	private PluginSettingsFactory getSettingsFactory() {
		return settingsFactory;
	}

	public void setSettingsFactory(PluginSettingsFactory settingsFactory) {
		this.settingsFactory = settingsFactory;
	}

	public void store(GlobalConfigurationWrapper data) {
		data.setSettingsFromData(getGlobalSettings());
	}

	public void store(RepositoryConfigurationWrapper data) {
		PluginSettings repositorySettings = getSettingsFactory().createSettingsForKey(data.getRepositoryKey());
		data.setSettingsFromData(repositorySettings);
	}

	public RepositoryConfigurationWrapper getConfigForRepository(String repositoryKey) {
		PluginSettings repositorySettings = getSettingsFactory().createSettingsForKey(repositoryKey);
		RepositoryConfigurationWrapper repositoryConfigurationWrapper = new RepositoryConfigurationWrapper();
		repositoryConfigurationWrapper.setDataFromSettings(repositorySettings);

		return repositoryConfigurationWrapper;
	}

	public GlobalConfigurationWrapper getGlobalConfig() {
		GlobalConfigurationWrapper globalConfigurationWrapper = new GlobalConfigurationWrapper();
		globalConfigurationWrapper.setDataFromSettings(getGlobalSettings());
		return globalConfigurationWrapper;
	}

}
