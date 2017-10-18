package org.symphonyoss.symphony.jcurl;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jpasseron
 */
public class NonProxyUtilityTest {

  @Test
  public void emptyNonProxyHostsTest() throws MalformedURLException{
	NonProxyUtility utility = new NonProxyUtility("");
	Assert.assertFalse(utility.bypassProxy(new URL("https://github.com/symphonyoss")));
	Assert.assertFalse(utility.bypassProxy(new URL("http://127.0.0.1:8080/example")));
  }
  
  @Test
  public void filledNonProxyHostsTest() throws MalformedURLException{
	NonProxyUtility utility = new NonProxyUtility("127.0.0.1");
	Assert.assertFalse(utility.bypassProxy(new URL("https://github.com/symphonyoss")));
	Assert.assertTrue(utility.bypassProxy(new URL("http://127.0.0.1:8080/example")));
  }
  
  @Test
  public void multipleNonProxyHostsTest() throws MalformedURLException{
	NonProxyUtility utility = new NonProxyUtility("127.0.0.1|github.com|foo.bar");
	Assert.assertFalse(utility.bypassProxy(new URL("https://symphony.foundation/")));
	Assert.assertTrue(utility.bypassProxy(new URL("http://127.0.0.1:8080/example")));
	Assert.assertTrue(utility.bypassProxy(new URL("https://github.com/symphonyoss")));
  }
  
  @Test
  public void wildcardNonProxyHostsTest() throws MalformedURLException{
	NonProxyUtility utility = new NonProxyUtility("127.*|github.com|*.bar");
	Assert.assertFalse(utility.bypassProxy(new URL("https://symphony.foundation/")));
	Assert.assertTrue(utility.bypassProxy(new URL("http://127.0.0.1:8080/example")));
	Assert.assertTrue(utility.bypassProxy(new URL("https://github.com/symphonyoss")));
	Assert.assertTrue(utility.bypassProxy(new URL("https://foor.bar/test")));
  }
  
}
