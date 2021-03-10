package recommendation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class databaseReader {
	
	//this methods reads all accounts and checks is anything matches
	public static String accountsReader(String username, String password){
		String sCurrentLine;
		String[] uCurrent;
		String type = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
			while((sCurrentLine = br.readLine()) != null) {
				uCurrent = sCurrentLine.split(";");
				if (username.equals(uCurrent[1]) && password.equals(uCurrent[2])) {
					System.out.println("You've succesfully logged in as: "+ username);
					type = uCurrent[3];
				}
			}
			br.close();
		}catch(IOException e){
			System.out.println("Could not log in");
		}
		return type;
	}
	
	

	// this method reads all biddings and returns them in an array
	public static bidding[] biddingsReader( ){
		String sCurrentLine;
		String[] uCurrent;
		bidding[] biddings = new bidding[0];
		try {
			BufferedReader br = new BufferedReader(new FileReader("houses.txt"));
			int j=0;
			while((sCurrentLine = br.readLine()) != null) j++;
			biddings = new bidding[j];
			int i = 0;
			while((sCurrentLine = br.readLine()) != null) {
				uCurrent = sCurrentLine.split(";");
				bidding b = new bidding(Integer.parseInt(uCurrent[0]),Double.parseDouble(uCurrent[1]),Integer.parseInt(uCurrent[2]),
										Integer.parseInt(uCurrent[3]));
				biddings[i] = b;
				i++;
			}
			br.close();
			
		}catch(IOException e){
			System.out.println("Could not read houses");
		}
		return biddings;
	}
	
	//this method reads all house objects into an array
	public static house[] housesReader( ){
		String sCurrentLine;
		String[] uCurrent;
		house[] houses = new house[0];
		try {
			BufferedReader br = new BufferedReader(new FileReader("houses.txt"));
			int j=0;
			while((sCurrentLine = br.readLine()) != null) j++;
			houses = new house[j];
			int i = 0;
			while((sCurrentLine = br.readLine()) != null) {
				uCurrent = sCurrentLine.split(";");
				house h = new house(Boolean.parseBoolean(uCurrent[0]), Integer.parseInt(uCurrent[1]),
						Integer.parseInt(uCurrent[2]),Integer.parseInt(uCurrent[3]),Double.parseDouble(uCurrent[4]), Double.parseDouble(uCurrent[5]),
						uCurrent[6],uCurrent[7],Integer.parseInt(uCurrent[8]), Integer.parseInt(uCurrent[9]), Integer.parseInt(uCurrent[10]));
				houses[i] = h;
				i++;
			}
			br.close();
			
		}catch(IOException e){
			System.out.println("Could not read houses");
		}
		return houses;
	}
	
	//this method reads in a single house object
	public static house houseReader(int houseId) {
		String sCurrentLine;
		String[] uCurrent;
		house h = new house();
		try {
			BufferedReader br = new BufferedReader(new FileReader("houses.txt"));
			while((sCurrentLine = br.readLine()) != null) {
				uCurrent = sCurrentLine.split(";");
				if (Integer.parseInt(uCurrent[8]) == houseId) {
				h = new house(Boolean.parseBoolean(uCurrent[0]), Integer.parseInt(uCurrent[1]),
						Integer.parseInt(uCurrent[2]),Integer.parseInt(uCurrent[3]),Double.parseDouble(uCurrent[4]), Double.parseDouble(uCurrent[5]),
						uCurrent[6],uCurrent[7],Integer.parseInt(uCurrent[8]), Integer.parseInt(uCurrent[9]), Integer.parseInt(uCurrent[10]));
			}}
			br.close();
			
		}catch(IOException e){
			System.out.println("Could not read houses");
		}
		return h;
	}

	//this method sets the houseCounter, userCounter and biddingCounter in recommendation class
	public static void generalReader(){
		String sCurrentLine;
		String[] uCurrent;
		try {
			BufferedReader br = new BufferedReader(new FileReader("general.txt"));
			while((sCurrentLine = br.readLine()) != null) {
				uCurrent = sCurrentLine.split(";");
				recommendation.houseCounter = Integer.parseInt(uCurrent[0]);
				recommendation.biddingCounter = Integer.parseInt(uCurrent[1]);
				recommendation.userCounter = Integer.parseInt(uCurrent[2]);
			}
			br.close();
		}catch(IOException e){
			System.out.println("Could not log in");
		}
	}



}

