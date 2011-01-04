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
import name.mariomueller.crucible.plugins.websvn.data.GlobalConfigurationWrapper;
import name.mariomueller.crucible.plugins.websvn.services.ConfigurationStoreService;

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
public class GlobalConfigurationServlet extends HttpServlet {

	private VelocityHelper velocityHelper;

	private ConfigurationStoreService storeService;

	public ConfigurationStoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(ConfigurationStoreService storeService) {
		this.storeService = storeService;
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
		GlobalConfigurationWrapper globalConfigurationWrapper = getStoreService().getGlobalConfig();


		params.put("baseUrl", globalConfigurationWrapper.getBaseUrl());
		params.put("path", globalConfigurationWrapper.getPath());
		params.put("query", globalConfigurationWrapper.getQuery());

		params.put("servletUrl", req.getContextPath() + getServletConfig().getInitParameter("path"));

		getVelocityHelper().renderVelocityTemplate("global_settings.vm", params, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("decorator", "atl.admin");
		resp.setContentType("text/html");

		Map<String, Object> params = new HashMap<String, Object>();
		GlobalConfigurationWrapper globalConfig = getStoreService().getGlobalConfig();

		globalConfig.setBaseUrl(req.getParameter("websvn_baseUrl"));
		globalConfig.setPath(req.getParameter("websvn_path"));
		globalConfig.setQuery(req.getParameter("websvn_query"));
		getStoreService().store(globalConfig);

		params.put("baseUrl", globalConfig.getBaseUrl());
		params.put("path", globalConfig.getPath());
		params.put("query", globalConfig.getQuery());

		params.put("servletUrl", req.getContextPath() + getServletConfig().getInitParameter("path"));

		getVelocityHelper().renderVelocityTemplate("global_settings.vm", params, resp.getWriter());
	}
}
