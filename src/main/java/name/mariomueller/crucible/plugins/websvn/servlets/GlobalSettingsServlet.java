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
 * Date: 30.12.10
 * Time: 21:15
 */
public class GlobalSettingsServlet extends HttpServlet {

	private PluginSettingsFactory settingsFactory;

	private VelocityHelper velocityHelper;

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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("decorator", "atl.admin");
		resp.setContentType("text/html");
		Map<String, Object> params = new HashMap<String, Object>();
		PluginSettings settings = settingsFactory.createGlobalSettings();

		String baseUrl = (String) settings.get("websvn.baseUrl");
		String path = (String) settings.get("websvn.path");
		String query = (String) settings.get("websvn.query");

		if (baseUrl == null) {
			baseUrl = "";
		}

		if (path == null) {
			path = "";
		}

		if (query == null) {
			query = "";
		}

		params.put("baseUrl", baseUrl);
		params.put("path", path);
		params.put("query", query);

		params.put("servletUrl", req.getContextPath() + getServletConfig().getInitParameter("path"));

		velocityHelper.renderVelocityTemplate("global_settings.vm", params, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("decorator", "atl.admin");
		resp.setContentType("text/html");

		PluginSettings settings = settingsFactory.createGlobalSettings();
		Map<String, Object> params = new HashMap<String, Object>();

		String baseUrl = req.getParameter("websvn_baseUrl");
		String path = req.getParameter("websvn_path");
		String query = req.getParameter("websvn_query");

		if (baseUrl == null) {
			baseUrl = "";
		}

		if (path == null) {
			path = "";
		}

		if (query == null) {
			query = "";
		}

		settings.put("websvn.baseUrl", baseUrl);
		settings.put("websvn.path", path);
		settings.put("websvn.query", query);

		params.put("baseUrl", baseUrl);
		params.put("path", path);
		params.put("query", query);

		params.put("servletUrl", req.getContextPath() + getServletConfig().getInitParameter("path"));

		velocityHelper.renderVelocityTemplate("global_settings.vm", params, resp.getWriter());
	}
}
