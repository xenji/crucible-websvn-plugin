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

import com.atlassian.crucible.spi.services.RepositoryService;
import com.atlassian.fisheye.plugin.web.helpers.VelocityHelper;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mario
 * Date: 31.12.10
 * Time: 10:54
 */
public class RepositorySettingsServlet extends HttpServlet {

	private PluginSettingsFactory settingsFactory;

	private VelocityHelper velocityHelper;

	private RepositoryService repositoryService;

	public PluginSettingsFactory getSettingsFactory() {
		return settingsFactory;
	}

	public void setSettingsFactory(PluginSettingsFactory settingsFactory) {
		this.settingsFactory = settingsFactory;
	}

	public VelocityHelper getVelocityHelper() {
		return velocityHelper;
	}

	public void setVelocityHelper(VelocityHelper velocityHelper) {
		this.velocityHelper = velocityHelper;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("decorator", "atl.admin");
		resp.setContentType("text/html");

		String r = req.getParameter("r");

		Map<String, Object> params = new HashMap<String, Object>();
		PluginSettings settings = settingsFactory.createSettingsForKey(r);

		String repo = (String) settings.get("websvn.repo");

		if (repo == null) {
			repo = "";
		}

		params.put("r", r);
		params.put("repo", repo);
		params.put("servletUrl", req.getContextPath() + getServletConfig().getInitParameter("path"));
		velocityHelper.renderVelocityTemplate("repository_settings.vm", params, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("decorator", "atl.admin");
		resp.setContentType("text/html");

		String r = req.getParameter("websvn_r");
		String repo = req.getParameter("websvn_repo");

		Map<String, Object> params = new HashMap<String, Object>();
		PluginSettings settings = settingsFactory.createSettingsForKey(r);

		if (repo == null) {
			repo = "";
		}

		settings.put("websvn.repo", repo);

		params.put("r", r);
		params.put("repo", repo);
		params.put("servletUrl", req.getContextPath() + getServletConfig().getInitParameter("path"));
		velocityHelper.renderVelocityTemplate("repository_settings.vm", params, resp.getWriter());
	}
}
