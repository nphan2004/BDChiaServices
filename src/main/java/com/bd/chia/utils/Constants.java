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
	public static final Integer PLOTS_SIZE_MULTIPLIER = 30;
}
