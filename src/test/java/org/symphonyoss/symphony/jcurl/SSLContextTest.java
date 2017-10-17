package org.symphonyoss.symphony.jcurl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateParsingException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * A temporary hardCoded test to investigate
 * @author jeremp
 */
public class SSLContextTest {

  @Test
  @Ignore
  public void testConnect() throws IOException, CertificateParsingException{    
    JCurl jCurl = JCurl.builder().method(JCurl.HttpMethod.GET)            
            .trusttype("jks")
            .truststore("E:/telechargements/jpn.jks")
            .trustpass("jpn")
            .keystore("E:/telechargements/jpn.jks")
            .storetype("jks")
            .storepass("jpn")            
            .build();
    
    HttpURLConnection connection = jCurl.connect("https://www.laposte20projetspour2020.fr/fr");
    JCurl.Response response = jCurl.processResponse(connection);
    System.out.println("content=\n"+response.getOutput());
    
  }
  
}
