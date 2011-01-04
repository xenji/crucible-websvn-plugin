package name.mariomueller.crucible.plugins.websvn.data;

import com.atlassian.sal.api.pluginsettings.PluginSettings;

/**
 * User: mario
 * Date: 04.01.11
 * Time: 20:31
 */
public class RepositoryConfigurationWrapper {

	private String repositoryKey;

	private String contextPath;

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		if (contextPath == null) {
			contextPath = "";
		}
		this.contextPath = contextPath;
	}

	public String getRepositoryKey() {
		return repositoryKey;
	}

	public void setRepositoryKey(String repositoryKey) {
		this.repositoryKey = repositoryKey;
	}

	public void setDataFromSettings(PluginSettings settings) {
		setContextPath((String) settings.get("websvn.repo"));
	}

	public void setSettingsFromData(PluginSettings settings) {
		settings.put("websvn.repo", getContextPath());
	}
}
