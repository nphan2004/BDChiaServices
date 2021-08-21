package com.bd.chia.services;

import java.net.URLEncoder;
import java.time.Duration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bd.chia.config.TelegramConfigure;

@Component
public class TelegramBotService {
	private static final Logger log = LoggerFactory.getLogger(TelegramBotService.class);
	private static final String TELEGRAM_URL = "https://api.telegram.org/";
	
	private RestTemplate restTemplate;
	
	@Autowired
	private TelegramConfigure telegramConfigure;
	
	@Autowired
    public TelegramBotService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(10)).build();
    }

	private String createBaseTelegramLink() {
		StringBuffer sb = new StringBuffer();
		sb.append(TELEGRAM_URL);
		sb.append("bot");
		sb.append(telegramConfigure.getToken());
		
		return sb.toString();
	}
	
	private String createSendLink(String message) {
		StringBuffer sb = new StringBuffer();
		sb.append(createBaseTelegramLink());
		sb.append("/sendMessage?");
		sb.append("chat_id=");
		sb.append(telegramConfigure.getChatId());
		sb.append("&text=");
		sb.append(URLEncoder.encode(message));
		return sb.toString();
	}
	
	public void sendNotification(String message) {
		HttpEntity entity = new HttpEntity(null);
		restTemplate.exchange(createSendLink(message), HttpMethod.GET, entity, String.class);
	}
	
	@PostConstruct
	public void init() {
		
		if(telegramConfigure.getToken()==null) {
			log.error("Please pass in telegram.token environment variable");
		}
		
		if(telegramConfigure.getChatId()==null) {
			log.error("Please pass in telegram.chatid environment variable");
		}
		
		sendNotification("I will let you know when your pool win Chia.");
	}
}
