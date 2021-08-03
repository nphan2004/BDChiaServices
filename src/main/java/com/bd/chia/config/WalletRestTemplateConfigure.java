package com.bd.chia.config;

//@Component
public class WalletRestTemplateConfigure {
	/*
	//@Bean("WalletRestTemplate")
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
	*/
}
