package recommendation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class user{
	static Scanner inputDbl = new Scanner(System.in);
	static Scanner inputInt = new Scanner(System.in);
	static Scanner inputBool = new Scanner(System.in);
	static Scanner inputStr = new Scanner(System.in);
	int userId;
	String username;
	String password;
	
	public void viewHouseList() {
		
		//This code is identical to the seller.addhouse() except for the use of the resulting house object
		house desiredHouse = new house();
		
		//list of the house attributes the user can specify
		String[] houseAttributes = {"available garden","Number of rooms", "Number of bathrooms", "Number of bedrooms", 
									"Number of floors", "Price", "Living area", "Energy label","exit"};
		// create arraylist with a loop to keep track of the specified attributes in wile loop below
		ArrayList<Integer> atbs = new ArrayList<Integer>() ;
		for (int i=0 ; i < houseAttributes.length ; i++) atbs.add(i);
		
		int userChoice = 100;
		// if the user wants to stop specifying attributes, s/he fills -1 in to exit
		while (userChoice != -1) {
			System.out.println("Do you want to specify a characteristic of your home?");
			for (int i = 0; i < atbs.size(); i++) {
				int j = atbs.get(i);
				System.out.println("* "+ houseAttributes[j] + "("+ j+")");
			}
			System.out.println("Type the attribute number to specify:");
			userChoice = inputInt.nextInt();
			switch (userChoice) {
			case 0: //hasGarden
				System.out.println("Does your house have a garden? (true/false)");
				desiredHouse.hasGarden = inputBool.nextBoolean();
				break;
			case 1:
				System.out.println("How many rooms does your house have?");
				desiredHouse.noOfRooms = inputInt.nextInt();
				break;
			case 2:
				System.out.println("How many bathrooms does your house have?");
				desiredHouse.noOfBathrooms = inputInt.nextInt();
				break;
			case 3:
				System.out.println("How many bathrooms does your house have?");
				desiredHouse.noOfBathrooms = inputInt.nextInt();
				break;
			case 4:
				System.out.println("How many floors does your house have?");
				desiredHouse.floors = inputInt.nextInt();
				break;
			case 5:
				System.out.println("What is the asking price?");
				desiredHouse.price = inputInt.nextInt();
				break;
			case 6:
				System.out.println("What is the living area (in sqr meters)?");
				desiredHouse.livingArea = inputInt.nextInt();
				break;
			case 7:
				System.out.println("What is the energy label of your house?");
				desiredHouse.energyLabel = inputStr.nextLine();
				break;
			case 8:
				userChoice = -1;
				break;
			}
			if (userChoice != -1) atbs.remove(atbs.indexOf(userChoice));
		}
		house[] houses = house.housesReader();
		
		// The following code will compute the manhattan distance from the desired house
		// 		to the available houses. The price and living area are normalized with a 
		// 		Z-transformation to mitigate the effect of high variance on the results.
		// For useability on all machines, no third party libraries (like StatUtils) are
		// 		used and therefore statistics are manually computed with loops.
		// Loops are written twice and not put into method because the calculations are 
		//		done only twice. When scaling up, a new method will be created to support
		//		bigger number of dimensions.
		
		//calculate mean
		double[] prices = new double[houses.length];
		double priceSum=0;
		double[] lArea = new double[houses.length];
		double livingAreaSum = 0;
		for (int i=0; i < houses.length; i++) {
				priceSum += houses[i].price;
				livingAreaSum = houses[i].livingArea;
		}
		double meanPrice = priceSum / houses.length;
		double meanlivingArea = livingAreaSum / houses.length;
		
		//Calculate variances
		//change object reference names for clarification purposes
		double[] pricesVariances = prices;
		double[] livingAreaVariances = lArea;
		//loop to compute variance per value
		for (int i=0; i < houses.length; i++) {
			pricesVariances[i] = Math.abs(houses[i].price) - meanPrice;
			pricesVariances[i] = Math.pow(prices[i], 2);
			livingAreaVariances[i] = Math.abs(houses[i].livingArea) - meanlivingArea;
			livingAreaVariances[i] = Math.pow(prices[i], 2);
		}
		
		//Calculate standard deviation
		double priceSD =0;
		double livingAreaSD = 0;
		for (int i=0; i < houses.length; i++) {
			priceSD += houses[i].price;
			livingAreaSD = houses[i].livingArea;
		}
		priceSD = Math.sqrt(priceSD / houses.length);
		livingAreaSD = Math.sqrt(livingAreaSD / houses.length);
		
		
		
		// Manhattan Distance
		int[] recommendedHouses = new int[houses.length];
		
	}
	

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
			public static user[] userReader( ){
				String sCurrentLine;
				String[] uCurrent;
				user[] users = new user[0];
				try {
					user u = new user();
					BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
					int i = 0;
					while((sCurrentLine = br.readLine()) != null) {
						uCurrent = sCurrentLine.split(";");
						if(uCurrent[3] == "buyer") {
							u = new buyer();
							users[i].username = uCurrent[1];
							users[i].password = uCurrent[2];
						}else if(uCurrent[3] == "seller") {
							u = new seller();
							users[i].username = uCurrent[1];
							users[i].password = uCurrent[2];
						}else if(uCurrent[3] == "admin") {
							u = new admin();
							users[i].username = uCurrent[1];
							users[i].password = uCurrent[2];	
						}
							
						users[i] = u;
						i++;
					}
					br.close();
					
				}catch(IOException e){
					System.out.println("Could not read users");
				}
				return users;
			}
		
			public static void appendAccounts(int id,String username, String password, String type){
				try {
					PrintWriter wr = new PrintWriter(new BufferedWriter(
									 new FileWriter("accounts.txt",true)));				
					wr.println(id +";"+ username +";"+ password +";"+ type);
					wr.close();
				}catch(IOException e) {
					System.out.println("File could not be read or appended to");
				}
			}
			
			
			public static void overwriteUsersFile(user[] users) {
				try {
					PrintWriter wr = new PrintWriter(new BufferedWriter(
									 new FileWriter("biddings.txt",false)));				
					for (int i = 0; i < users.length; i++) {
						user u = users[i];
						if (u instanceof buyer) wr.println(u.username +";"+ u.password + ";" + u.userId +";buyer");
						else if (u instanceof seller) wr.println(u.username +";"+ u.password + ";" + u.userId +";seller");
						else if (u instanceof admin) wr.println(u.username +";"+ u.password + ";" + u.userId +";admin");
					}
					wr.close();
					
				}catch(IOException e) {
					System.out.println("File could not be overwritten");
				}
			}
}


