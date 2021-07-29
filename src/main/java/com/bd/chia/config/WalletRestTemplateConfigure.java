package com.bd.chia.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.function.Supplier;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class WalletRestTemplateConfigure {
	@Bean("WalletRestTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
	    char[] password = "secret".toCharArray();

//	    // Create a trust manager that does not validate certificate chains
//	    TrustManager[] trustAllCerts = new TrustManager[] { 
//	        new X509ExtendedTrustManager() {
//				
//				@Override
//				public X509Certificate[] getAcceptedIssuers() {
//					// TODO Auto-generated method stub
//					return new X509Certificate[0];
//				}
//				
//				@Override
//				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
//						throws CertificateException {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
//						throws CertificateException {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
//						throws CertificateException {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
//						throws CertificateException {
//					// TODO Auto-generated method stub
//					
//				}
//			}
//	    }; 
//	    
	    TrustManager[] trustAllCerts = new TrustManager[]{
	        new X509TrustManager(){
	          public X509Certificate[] getAcceptedIssuers(){ return null; }
	          public void checkClientTrusted(X509Certificate[] certs, String authType) {}
	          public void checkServerTrusted(X509Certificate[] certs, String authType) {}
	       }
	     };
	    
	    SSLContext sslContext = SSLContext.getInstance("SSLv2");
//	    SSLContext sslContext = SSLContextBuilder.create()	    		
//	            .loadKeyMaterial(keyStore("/home/bigdreams/.chia/mainnet/config/ssl/wallet/wallet.jks", password), password)
//	            .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
	    sslContext.init(null, trustAllCerts, null);

	    CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).build();
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(client);
	    
	    //return builder.requestFactory(clientHttpRequestFactory).build();
	    return new RestTemplate(clientHttpRequestFactory);
	}

	 private KeyStore keyStore(String file, char[] password) throws Exception {
	    KeyStore keyStore = KeyStore.getInstance("pkcs12");
	    File key = new File(file); //ResourceUtils.getFile(file);
	    try (InputStream in = new FileInputStream(key)) {
	        keyStore.load(in, password);
	    }
	    return keyStore;
	}
}
