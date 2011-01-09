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
