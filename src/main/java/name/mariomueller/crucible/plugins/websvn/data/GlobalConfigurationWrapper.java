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

package name.mariomueller.crucible.plugins.websvn.data;

import com.atlassian.sal.api.pluginsettings.PluginSettings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Defines the WebSVN global configuration as an
 * JAXB annotated bean. This bean is used for
 * the REST services and for the ConfigurationStoreService.
 *
 * @author Mario Mueller <mario.mueller.mac@me.com>
 */
@XmlRootElement(name = "websvn-global-configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class GlobalConfigurationWrapper {

	public GlobalConfigurationWrapper() {}

	private static final String DEFAULT_QUERY = "?op=revision&peg=#revision#&rev=#revision#";

	@XmlElement(name = "base-url")
	private String baseUrl;

	@XmlElement(name = "path")
	private String path;

	@XmlElement(name = "query")
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
		if (query == null || query.equals("")) {
			query = DEFAULT_QUERY;
		}
		return query;
	}

	public void setQuery(String query) {
		if (query == null || query.equals("")) {
			query = DEFAULT_QUERY;
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
