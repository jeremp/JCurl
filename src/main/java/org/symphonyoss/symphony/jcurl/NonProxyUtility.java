/*
 * Copyright 2016-2017 MessageML - Symphony LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.symphonyoss.symphony.jcurl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class takes a nonProxyHosts input and check if we have to bypass proxies for a given URL
 *
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/net/doc-files/net-properties.html">Networking Properties</a>
 * @author jeremp
 */
public class NonProxyUtility {

  private final String originalInput;
  private final List<Pattern> nonProxyHostPatterns;

  /**
   * Constructor
   *
   * @param nonProxyHosts hosts separated by pipes characters
   */
  public NonProxyUtility(String nonProxyHosts) {
	nonProxyHostPatterns = new ArrayList<>();
	this.originalInput = nonProxyHosts;
	// build the Pattern list from the given nonProxyHosts
	if (nonProxyHosts != null && nonProxyHosts.trim().length() > 0) {
	  String[] hostRegExps = nonProxyHosts.split("\\|");	  
	  for (String exp : hostRegExps) {
		// regular expression can't start with a *
		if (exp.startsWith("*")) {
		  exp = "." + exp;
		}		
		nonProxyHostPatterns.add( Pattern.compile(exp) );
	  }
	}
  }

  /**
   * Should we bypass proxy for this URL's host ?
   *
   * @param url
   * @return true if we should bypass proxy, false otherwise
   */
  public boolean bypassProxy(URL url) {
	for (Pattern hostPattern : nonProxyHostPatterns) {
	  if (hostPattern.matcher(url.getHost()).matches()) {
		return true;
	  }
	}
	return false;
  }

  public String getOriginalInput() {
	return originalInput;
  }

}
