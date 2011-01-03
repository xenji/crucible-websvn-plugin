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

package name.mariomueller.crucible.plugins.websvn.servlets;

import com.atlassian.crucible.spi.data.RepositoryData;
import com.atlassian.crucible.spi.data.SvnRepositoryData;
import com.atlassian.crucible.spi.services.RepositoryService;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Redirect Servlet
 *
 * @author Mario Mueller <mario.mueller@twt.de>
 * @version 1.0
 */
public class RedirectServlet extends HttpServlet {

	private RepositoryService repositoryService;

	private PluginSettingsFactory settingsFactory;

	private static final String DEFAULT_QUERY = "?op=revision&peg=#revision#&rev=#revision#";


	public PluginSettingsFactory getSettingsFactory() {
		return settingsFactory;
	}

	public void setSettingsFactory(PluginSettingsFactory settingsFactory) {
		this.settingsFactory = settingsFactory;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repoService) {
		this.repositoryService = repoService;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String rev = request.getParameter("rev");
		String repo = request.getParameter("repo");

		if (rev == null || repo == null) {
			response.sendRedirect(request.getHeader("Referer"));
		}

		repo = repo.replace("/", "");

		RepositoryData repoData = getRepositoryService().getRepository(repo);
		if (repoData instanceof SvnRepositoryData) {


			PluginSettings settings = getSettingsFactory().createGlobalSettings();
			String urlBaseUrl = (String) settings.get("websvn.baseUrl");
			String urlBasePath = (String) settings.get("websvn.path");
			String urlBaseQuery = (String) settings.get("websvn.query");

			PluginSettings repoSettings = getSettingsFactory().createSettingsForKey(repo);
			String urlRepoPath =  (String) repoSettings.get("websvn.repo");

			if (urlBaseQuery == null || urlBaseQuery.equals("")) {
				urlBaseQuery = DEFAULT_QUERY;
			}

			// URL
			StringBuilder sb = new StringBuilder(urlBaseUrl);

			// Path to WSVN
			sb.append(urlBasePath);

			// Path to repository
			sb.append(urlRepoPath).append("/");

			// Query String
			sb.append(urlBaseQuery.replace("#revision#", rev));

			// Send the redirect
			response.sendRedirect(sb.toString());
		} else {

			response.sendRedirect(request.getHeader("Referer"));
		}
	}
}
