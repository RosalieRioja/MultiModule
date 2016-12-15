package com;

import java.util.*;

public class Actions {

	private MapClass mapClass;
	private final int NUMBER_OF_ASCII_CHARS = 95;
	private final int ASCII_CODE_START = 32;

	public Actions() {
		mapClass = new MapClass();
	}//

	public void reset() {
		mapClass.resetMap();

		LinkedHashMap<String, String> hm = new LinkedHashMap();
		String key = "";
		String value = "";

		for(int i = 0; i < mapClass.getDefaultNumberOfRow(); i++) {
			for (int j = 0; j < mapClass.getDefaultNumberOfCol(); j++) {
				key = getRandomizedValues();
				value = getRandomizedValues();

				hm.put(key, value);
			}//for column

			mapClass.addToMap(hm);
			hm = new LinkedHashMap();
		}//for row
	
	}//reset

	private String getRandomizedValues() {
		String str = "";

		for (int k = 0; k < mapClass.getDefaultLengthOfValues(); k++) {
			str += randomChar();
		}//for each cell

		return str;
	}//

	private char randomChar() {
		int iRandomNum;

		//95 ascii characters starting from 32
		iRandomNum = ((int) (Math.random() * NUMBER_OF_ASCII_CHARS)) + ASCII_CODE_START;
		return (char) iRandomNum;	//get character

	}//randomize

	public ArrayList<String> print() {
		ArrayList<String> lstPrintString = mapClass.getMapAsString();
		int rowNum = 1;
		System.out.println("\nKEY = VALUE\n\t1\t\t2\t\t3");

		for(String str : lstPrintString) {
			
			System.out.print(rowNum++ + "\t");
			String value = str.substring(1, str.length()-1);
			String[] keyValue = value.split(", ");

			for(String pair : keyValue)
			{
				System.out.print(pair.substring(0, 3) + " = " + pair.substring(4, 7) + "\t");
			}

			System.out.print("\n");
		}

		return lstPrintString;
	}//print

	//add row | true if existing from file, false if manual
	public boolean addRow(boolean existing, String strMapline) {
		LinkedHashMap<String, String> hm = new LinkedHashMap();
		boolean success = false;
		
		if(existing) {
			strMapline = strMapline.substring(1, strMapline.length()-1);			
			String[] keyValue = strMapline.split(", ");

			//validate if cells from file have 3 columns
			if(keyValue.length == mapClass.getDefaultNumberOfCol()) {
				success = true;
			}
			else {
				success = false;
			}

			for(String pair : keyValue)
			{
				hm.put(pair.substring(0, 3), pair.substring(4, 7));
			}
		}
		else {
			String key, value;

			//ask user for input
			while(hm.size() != mapClass.getDefaultNumberOfCol()) {
				key = Validation.enterNumofChars("\nEnter key for column " + (hm.size()+1) + " : ", mapClass.getDefaultLengthOfValues());
				value = Validation.enterNumofChars("\nEnter value for column " + (hm.size()+1) + " : ", mapClass.getDefaultLengthOfValues());
				
				if(hm.get(key) != null) {
					System.out.println("Row cannot have duplicate keys. Please try again.");
				}
				else {
					hm.put(key, value);
				}
			}

			success = true;
		}

		if(success) {
			mapClass.addToMap(hm);
		}

		return success;
	}//addRow

	public void edit() {
		//
		int iRowIndex = 0;
		int iColIndex = 0;
		boolean existing = false;
		boolean success = false;
		String editCell = "";
		String strNewkey = "";
		String strNewValue = "";

		//validate if indexes are existing
		while(!existing) {
			System.out.print("\nEnter the indexes of the cell to edit\n");
		
			iRowIndex = Validation.enterInteger("Row index : ");
			iColIndex = Validation.enterInteger("Column index : ");

			if(iRowIndex <= mapClass.getMap().size() && iColIndex <= mapClass.getDefaultNumberOfCol() && iRowIndex > 0 && iColIndex > 0) {
				existing = true;
			}
			else {
				System.out.println("The indexes entered does not exist.");
			}
		}

		//locate key / cell
		LinkedHashMap<String, String> lhm = mapClass.getMap().get(iRowIndex-1);
		
		String str = lhm.toString();
		str = str.substring(1, str.length()-1);
		String[] keyValue = str.split(", ");

		//display
		System.out.println("\nEdit \"" + keyValue[iColIndex-1].substring(0, 3) + " = " + keyValue[iColIndex-1].substring(4, 7) + "\" to :");

		//get new values
		while(!success) {
			strNewkey = Validation.enterNumofChars("Enter new key : ", mapClass.getDefaultLengthOfValues());
			strNewValue = Validation.enterNumofChars("Enter new value : ", mapClass.getDefaultLengthOfValues());

			if(lhm.get(strNewkey) != null && !strNewkey.equals(keyValue[iColIndex-1].substring(0,3))) {
				System.out.println("Row cannot have duplicate keys. Please try again.");
			}
			else {
				success = true;
			}
		}

		//save to the map
		keyValue[iColIndex-1] = strNewkey + "=" + strNewValue;
		lhm.clear();

		for(String kv : keyValue) {
			lhm.put(kv.substring(0,3), kv.substring(4, 7));
		}
	}//edit

	public void search() {

		String strSearch = null;
		boolean found = false;
		int searchRow = 0;
		int searchCol = 0;

		strSearch = Validation.acceptString("\nSearch for : ");

		System.out.println("\nSearch results for \"" + strSearch + "\"\n");

		for(LinkedHashMap<String, String> lhm : mapClass.getMap()) {
			searchRow++;
			searchCol = 0;
			String str = lhm.toString();
			str = str.substring(1, str.length()-1);
			String[] keyValue = str.split(", ");

			for (String kv : keyValue) {
				searchCol++;
				kv = kv.substring(0,3) + kv.substring(4,7);

				if(kv.toLowerCase().contains(strSearch.toLowerCase())) {
					System.out.println("\"" + kv.substring(0,3) + " = " + kv.substring(3,6) 
						+ "\"\tRow " + searchRow + ", Column " + searchCol);
					found = true;
				}
			}
		}

		if(!found) {
			System.out.println("No results found.");
		}
	}//search

	public void sort() {
		//
		int rowNum = 0;
		boolean existing = false;

		while(!existing) {
			rowNum = Validation.enterInteger("\nEnter row index to sort : ");

			if(rowNum <= mapClass.getMap().size() && rowNum > 0) {
				existing = true;
			}
			else {
				System.out.println("Row does not exist.");
			}
		}

		//locate row
		LinkedHashMap<String, String> lhm = mapClass.getMap().get(rowNum-1);
		String str = lhm.toString();
		str = str.substring(1, str.length()-1);
		String[] keyValue = str.split(", ");

		//sort cells
		Arrays.sort(keyValue);
		lhm.clear();

		for (String kv : keyValue) {
			lhm.put(kv.substring(0,3), kv.substring(4, 7));
		}

	}//sort

}//
