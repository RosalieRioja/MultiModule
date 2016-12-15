package com;

import java.io.*;
import java.util.*;

public class Menu {
		
	FileReadandWrite ex = new FileReadandWrite();
	boolean reset = false;
	String temp = "";

	Actions action = new Actions();

	public Menu() {

		ex.readFile();

		//validate lines here
		////if invalid format or empty
		if(ex.getLines().isEmpty()) {
		////suggest resetting
			//do you want to reset? else exit
			reset = true;
		}

		System.out.println("\n  -------------\n | Collections |\n  -------------\n");
		
		startMenu(reset, ex.getLines());

	}//

	public void startMenu(boolean toreset, ArrayList<String> lines) {
		int iAction = 0;
		//ClassMap cm = new ClassMap();

		////validate contents of the file

		//if data is to reset
		if(toreset) {
			System.out.println("Contents are reset.");
			action.reset();
		}
		else {	//create list of array from file
			//make sure the line is in correct format
			try {
				boolean valid = false;

				for(String str : lines) {
					//validate if columns are 3
					valid = action.addRow(true, str);

					if(!valid) {
						System.out.println("Contents of the file are invalid.\nContents are reset.");
						action.reset();
						break;
					}
				}
			}
			catch(Exception ex) {
				System.out.println("Contents of the file are invalid.\nContents are reset.");
				action.reset();
			}
		}
		////validate contents of the file

		ex.writetoFile(action.print());

		while(iAction != 7) {
			System.out.println("\nActions (Enter the number of the desired action) :");
			System.out.print("(1) Search\n(2) Edit\n(3) Print \n(4) Reset\n(5) Add Row\n(6) Sort Row\n(7) Exit\n\n");

			//validate
			iAction = Validation.enterInteger("Action no.: ");

			//switch case call corresponding method
			switch(iAction) {

				case 1 : //call search method
					System.out.println("Search");
					action.search();
					break;
				case 2 : //call edit method
					System.out.println("Edit");
					action.edit();
					ex.writetoFile(action.print());
					break;
				case 3 : //call print method
					action.print();
					break;
				case 4 : //call reset/randomize method
					System.out.println("Reset");
					action.reset();
					ex.writetoFile(action.print());
					break;
				case 5 : //add row
					System.out.println("Add Row");
					action.addRow(false, null);
					ex.writetoFile(action.print());
					break;
				case 6 : //sort row
					System.out.println("Sort Row");
					action.sort();
					ex.writetoFile(action.print());
					break;
				case 7 : //exit
					//System.exit(0);
					System.out.println("Exit\n");
					break;
				default : 
					System.out.println("There is no action for that number.");	//then print menu again
					break;
			}//switch
		}//while

	}//

}//
