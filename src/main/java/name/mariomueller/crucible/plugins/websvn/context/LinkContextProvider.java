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

package name.mariomueller.crucible.plugins.websvn.context;

import com.atlassian.core.util.map.EasyMap;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;

import java.util.Map;

/**
 * User: mario
 * Date: 07.01.11
 * Time: 08:31
 */
public class LinkContextProvider implements ContextProvider {

	public void init(Map<String, String> params) throws PluginParseException {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public Map<String, Object> getContextMap(Map<String, Object> context) {
		return EasyMap.build();
	}
}
