package com;

import java.util.*;

public class MapClass {

	private ArrayList<LinkedHashMap<String, String>> mapArray;
	private final int DEFAULT_NUMBER_OF_ROW = 3;
	private final int DEFAULT_NUMBER_OF_COL = 3;
	private final int DEFAULT_LENGTH_OF_VALUES = 3;

	public MapClass() {
		resetMap();
	}//

	public ArrayList<String> getMapAsString() {
		ArrayList<String> lstString = new ArrayList<String>();

		for(HashMap map : mapArray) {
			lstString.add(map.toString());
		}
		
		return lstString;
	}

	public ArrayList<LinkedHashMap<String, String>> getMap() {
		return mapArray;
	}

	public void resetMap() {
		mapArray = new ArrayList<LinkedHashMap<String, String>>();
	}

	public void addToMap(LinkedHashMap<String, String> hm) {
		mapArray.add(hm);
	}

	public int getDefaultNumberOfRow() {
		return DEFAULT_NUMBER_OF_ROW;
	}

	public int getDefaultNumberOfCol() {
		return DEFAULT_NUMBER_OF_COL;
	}

	public int getDefaultLengthOfValues() {
		return DEFAULT_LENGTH_OF_VALUES;
	}
}//
