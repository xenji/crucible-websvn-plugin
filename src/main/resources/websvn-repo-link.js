/*
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
window.WSVNL = window.WSVNL || {};
/*
javascript:window.open(FECRU.pageContext +
			"/plugins/servlet/websvn-redirect?changesetId=${helper.changeset.csid}&amp;repositoryKey=${helper.repository.path}",
			"WebSVN", "width=1024,height=768,status=yes,scrollbars=yes,resizable=yes,menubar=yes,location=yes");
 */

/**
 * Opens a new window for the WebSVN View
 * @param changesetId The ChangesetId
 * @param repositoryPath The path in the repository
 * @return void
 */
WSVNL.openWindow = function(changesetId, repositoryPath) {
	var baseUrl = FECRU.pageContext + "/plugins/servlet/websvn-redirect";
	var url = baseUrl + "?changesetId=" + changesetId + "&repositoryKey=" + repositoryPath;
	window.open(url, "WebSVN", "width=1024,height=768,status=yes,scrollbars=yes,resizable=yes,menubar=yes,location=yes");
}
