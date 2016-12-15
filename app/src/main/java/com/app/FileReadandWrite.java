package com;

import java.io.*;
import java.util.*;

public class FileReadandWrite {
	private final String FILENAME;
	private final File file;
	private ArrayList<String> lines;

	//Console console = System.console();

	//String temp = "";

	public FileReadandWrite() {
		FILENAME = "File.txt";
		file = new File(FILENAME);
		lines = new ArrayList<String>();
	}//

	public ArrayList<String> getLines() {
		return lines;
	}

	private void setLines(ArrayList<String> linesParam) {
		lines = linesParam;
	}

	public void readFile() {
	    ArrayList<String> filelines = new ArrayList<String>();

		try {
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		    	filelines.add(line);
		    }
		}
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                file + "'");                
        }
        catch (IOException e) {
			e.printStackTrace();
		}

	    setLines(filelines);
	}//

	public void writetoFile(ArrayList<String> stringArray) {

		try {
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for(String str : stringArray) {
				bw.write(str);
				bw.newLine();
			}
			
			bw.close();
		}
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                file + "'");                
        }
        catch (IOException e) {
			e.printStackTrace();
		}
	}//

}//
