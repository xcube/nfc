package org.xcube.nfc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtil {
	public static String readFromStream(InputStream in) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder result = new StringBuilder();
		try {
		String line = null;
		while (null != (line = reader.readLine())) {
			result.append(line);
			result.append("\n");
		}
		} catch (IOException e) {
			
		}
		return result.toString();
	}
}
