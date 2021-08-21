package com.bd.chia.utils;

import java.math.BigDecimal;

public class Constants {
	public static final BigDecimal GiB = new BigDecimal(1024).pow(3);
	public static final BigDecimal TiB = new BigDecimal(1024).pow(4);
	public static final BigDecimal PiB = new BigDecimal(1024).pow(5);
	public static final BigDecimal EiB = new BigDecimal(1024).pow(6);
	public static final BigDecimal ZiB = new BigDecimal(1024).pow(7);
	public static final BigDecimal YiB = new BigDecimal(1024).pow(8);
	
	public static final Long PLOTS_SIZE = 109000000000L;
	public static final Integer PLOTS_SIZE_MULTIPLIER = 39;
	
	public static final Long HOUR_12 = (12 * 60 *60 * 1000L);
	public static final Long HOUR_24 = (24 * 60 *60 * 1000L);
	public static final Long DAY_7 = (7 * 24 * 60 *60 * 1000L);
	public static final Long DAY_30 = (30 * 24 * 60 *60 * 1000L);
	
	public static final Integer ONE_DAY_MS = 24 * 60 * 60 * 1000;	
}
