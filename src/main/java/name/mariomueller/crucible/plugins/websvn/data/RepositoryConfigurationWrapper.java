package name.mariomueller.crucible.plugins.websvn.data;

import com.atlassian.sal.api.pluginsettings.PluginSettings;

import javax.xml.bind.annotation.*;

/**
 * User: mario
 * Date: 04.01.11
 * Time: 20:31
 */
@XmlRootElement(name = "websvn-repository-configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class RepositoryConfigurationWrapper {

	public RepositoryConfigurationWrapper() {}

	@XmlAttribute(name = "repository-key")
	private String repositoryKey;

	@XmlElement(name = "context-path")
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
