package com.bd.chia.utils;

public class HelperUtil {
	public static final String remove0x(String launcherId) {
		if(launcherId.startsWith("0x") || launcherId.startsWith("0X")) {
			return launcherId.substring(2);
		}
		
		return launcherId;
	}
}
