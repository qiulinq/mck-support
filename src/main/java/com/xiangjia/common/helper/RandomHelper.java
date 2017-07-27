package com.xiangjia.common.helper;

public class RandomHelper {

	public static String randomNumber(int mutileNumber) {
		int number = (int) ((Math.random() * 9 + 1) * mutileNumber);

		return number + "";
	}
}
