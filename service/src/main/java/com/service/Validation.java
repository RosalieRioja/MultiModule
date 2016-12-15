package com;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.Console;

public class Validation {

	private static Console console = System.console();

	//validate input to integer only
	public static int enterInteger(String label) {
		String value = "";
		boolean success = false;

		while(!success) {
			System.out.print(label);
			value = console.readLine();
			success = NumberUtils.isParsable(value);

			if(!success) {
				System.out.println("Please enter an integer.");
			}
		}

		return (int) (Double.parseDouble(value));

	}//end enterInteger

	//validate input to approx. num of characters
	public static String enterNumofChars(String label, int lengthOfChars) {
		String value = "";
		boolean success = false;

		while(!success) {
			System.out.print(label);
			value = console.readLine();

			try {
				Validate.isTrue(value.length() == lengthOfChars);
				success = true;
			}
			catch(IllegalArgumentException ex) {
				System.out.println("Please enter " + lengthOfChars + " characters.");
			}
		}
		return value;
	}//end enter3Chars

	public static String acceptString(String label) {
		String value = "";
		boolean success = false;

		while(!success) {
			System.out.print(label);
			value = console.readLine();

			try{
				Validate.notEmpty(value);
				success = true;
			}
			catch(IllegalArgumentException ex) {
				System.out.println("Input cannot be empty.");
			}
			catch(NullPointerException ex) {
				System.out.println("Input cannot be null.");
			}
		}

		return value;
	}//end acceptSearchString
}
