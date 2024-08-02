package com.nothing.apps.find_my_device_ws.utils;

import java.util.UUID;


public class RandomGenerator {

	public static String produceRandomNumber(int length) {
		String numbers = "";
		String randomNumber = "";
		String prefix = UUID.randomUUID().toString();
		String randomString = prefix.replace("-", "");
		randomString = randomString + Long.MAX_VALUE;
		numbers = extractNumbers(randomString);

		randomNumber = formalizeToLength(length, numbers);

		return randomNumber;

	}

	private static String formalizeToLength(int length, String numbers) {
		String randomNumber = "";
		for (int i = 0; i < length; i++) {

			randomNumber += numbers.charAt(i);

		}
		return randomNumber;
	}

	private static String extractNumbers(String randomString) {
		String number = "";
		for (int j = 0; j < randomString.length(); j++) {
			char character = randomString.charAt(j);
			switch (Character.toLowerCase(character)) {
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
				// do nothing
				break;
			default:
				number = number + character;
			}

		}
		return number;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			System.out.println(produceRandomNumber(10));

		}
	}

}
