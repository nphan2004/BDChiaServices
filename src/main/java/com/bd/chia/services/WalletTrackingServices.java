package com.bd.chia.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.tomcat.websocket.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.bd.chia.dto.DaemonRequest;
import com.bd.chia.websocket.ChiaMessageHandler;
import com.fasterxml.jackson.annotation.JsonProperty;



@Component
public class WalletTrackingServices {
	private static final Logger log = LoggerFactory.getLogger(WalletTrackingServices.class);
	private static final int CONNECT_TIME_OUT = 10;
	
	char[] password = "secret".toCharArray();
	
	ChiaMessageHandler handler;
	
//	@Autowired
//	@Qualifier("WalletRestTemplate")
//    private RestTemplate restTemplate;
    
//	@Autowired
//    public WalletTrackingServices(RestTemplateBuilder restTemplateBuilder) {
//		SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(null, null, null)
//        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(CONNECT_TIME_OUT)).build();
//        //setupCompression();
//    }
	
	public class WalletRequest {
		Integer walletId;

		@JsonProperty("wallet_id")
		public Integer getWalletId() {
			return walletId;
		}

		public void setWalletId(Integer walletId) {
			this.walletId = walletId;
		}
	}
	
	private WebSocketStompClient stompClient;
	
	 private KeyStore keyStore(String file, char[] password) throws Exception {
		    KeyStore keyStore = KeyStore.getInstance("pkcs12");
		    File key = new File(file); //ResourceUtils.getFile(file);
		    try (InputStream in = new FileInputStream(key)) {
		        keyStore.load(in, password);
		    }
		    return keyStore;
		}
	 
	public void connect() throws Exception {
		log.info("Connect to Chia daemon");
		if(stompClient!=null && stompClient.isRunning()) {
			log.info("Already connected");
			return;
		}
		
        // Create all-trusting host name verifier
//        HostnameVerifier allHostsValid = new HostnameVerifier() {
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        };
//        
//		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
//	    TrustManager[] trustAllCerts = new TrustManager[]{
//		        new X509TrustManager(){
//		          public X509Certificate[] getAcceptedIssuers(){ return null; }
//		          public void checkClientTrusted(X509Certificate[] certs, String authType) {}
//		          public void checkServerTrusted(X509Certificate[] certs, String authType) {}
//		       }
//		     };
		    
	    TrustManager[] trustAllCerts = new X509ExtendedTrustManager[] {
	    	new X509ExtendedTrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}	    		
	    	}
	    };
//		    SSLContext sslContext = SSLContext.getInstance("SSLv2");
		    SSLContext sslContext = SSLContextBuilder.create()	    		
		            //.loadKeyMaterial(keyStore("D:\\Projects\\Bigdreams\\daemon.jks", password), password)
		            .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
		    sslContext.init(null, trustAllCerts, null);
		    
		    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		    
//		    SslContextFactory.Client ssl = new SslContextFactory.Client(); // ssl config
//		    ssl.setEndpointIdentificationAlgorithm(null); // disable endpoint identification algorithm.
//		    WebSocketClient client = new WebSocketClient(ssl); // give ssl config to client
		    
		Map<String, Object> useProperties = new HashMap<String, Object>();
		useProperties.put(Constants.SSL_CONTEXT_PROPERTY, sslContext);
		
		StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
		webSocketClient.setUserProperties(useProperties);

        stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());
        handler = new ChiaMessageHandler();

       // stompClient.connect("wss://chachia:55400/", handler);
//        StompSession session = stompClient.connect("wss://chachia:55400", handler).get();
        //DaemonRequest dr = new DaemonRequest();
        //session.send("", dr);
		stompClient.start();
		
		log.info("Connect++");
		//sendResponse(rigId, "Connection established.");
	}
	
	public void disconnect() {
		log.info("++Disconnect");
		stompClient.stop();
		//handler.disconnect();
		log.info("++Disconnect Done.");
	}
	
	//@PostConstruct
	public void init() {
		log.info("Init");
		try {
			//connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Scheduled(fixedDelay=30000, initialDelay=100)
//	public void updateWinningData() {
//		WalletRequest wr = new WalletRequest();
//		wr.setWalletId(1);
////		String data = restTemplate.getForObject("https://localhost:9256/get_transactions", String.class);
//		String data = restTemplate.postForObject("https://localhost:9256/get_transactions", "{\"wallet_id\": 1}", String.class);
//		System.out.println("Update Winning Data : " + data);
//	}
}
