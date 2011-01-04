package name.mariomueller.crucible.plugins.websvn.data;

import com.atlassian.sal.api.pluginsettings.PluginSettings;

/**
 * User: mario
 * Date: 04.01.11
 * Time: 20:31
 */
public class GlobalConfigurationWrapper {

	private String baseUrl;

	private String path;

	private String query;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		if (baseUrl == null) {
			baseUrl = "";
		}

		this.baseUrl = baseUrl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if (path== null) {
			path = "";
		}
		this.path = path;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		if (query == null) {
			query = "";
		}
		this.query = query;
	}

	public void setDataFromSettings(PluginSettings settings) {
		setBaseUrl((String) settings.get("websvn.baseUrl"));
		setPath((String) settings.get("websvn.path"));
		setQuery((String) settings.get("websvn.query"));
	}

	public void setSettingsFromData(PluginSettings settings) {
		settings.put("websvn.baseUrl", getBaseUrl());
		settings.put("websvn.path", getPath());
		settings.put("websvn.query", getQuery());
	}
}
