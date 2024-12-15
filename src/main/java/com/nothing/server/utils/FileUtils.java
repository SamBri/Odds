package com.nothing.server.utils;

public class FileUtils {

	public static String grabFileExtension(String originalFilename) {

		String[] vars = originalFilename.split("\\.");

		return "."+vars[1];

	}

}
