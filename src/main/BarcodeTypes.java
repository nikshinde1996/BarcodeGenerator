package main;

import java.util.*;

public class BarcodeTypes {
	public static LinkedHashMap<String, Integer> enctypes = new LinkedHashMap<>();
	public static String[] encodingTypes = { " Codabar ", " Code 11 ", " Code 2 of 5 ", " Code 39 ", " Code 93 " };

	public BarcodeTypes() {
		for (int i = 0; i < encodingTypes.length; i++) {
			enctypes.put(encodingTypes[i], i + 1);
		}
	}

	public LinkedHashMap<String, Integer> getEncodingTypesMap() {
		return enctypes;
	}

	public String[] getEncodingTypes() {
		return encodingTypes;
	}

	public static int getValue(String str) {
		return enctypes.get(str).intValue();
	}
}
