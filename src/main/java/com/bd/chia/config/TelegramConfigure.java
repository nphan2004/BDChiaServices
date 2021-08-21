package com.bd.chia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class TelegramConfigure {
    @Autowired
    private Environment env;
	
	public String getToken() {
		return env.getProperty("telegram.token");
	}

	public String getChatId() {
		return env.getProperty("telegram.chatid");
	}
}
