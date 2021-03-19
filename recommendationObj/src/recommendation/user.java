package recommendation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class user{

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
			System.out.println("To find your dreamhome, please fill in all criteria.");
			for (int i = 0; i < atbs.size(); i++) {
				int j = atbs.get(i);
				System.out.println("* "+ houseAttributes[j] + "("+ j+")");
			}
			System.out.println("Type the attribute number to specify:");
			
			//
			//userChoice = inputInt.nextInt();
			// option 1
			userChoice = recommendation.getInputInteger();
			
			switch (userChoice) {
			case 0: //hasGarden
				System.out.println("Does your house have a garden? (true/false)");
				desiredHouse.hasGarden = recommendation.getInputBool();
				break;
			case 1:
				System.out.println("How many rooms does your house have?");
				desiredHouse.noOfRooms = recommendation.getInputInteger();
				break;
			case 2:
				System.out.println("How many bathrooms does your house have?");
				desiredHouse.noOfBathrooms = recommendation.getInputInteger();
				break;
			case 3:
				System.out.println("How many bathrooms does your house have?");
				desiredHouse.noOfBedrooms = recommendation.getInputInteger();
				break;
			case 4:
				System.out.println("How many floors does your house have?");
				desiredHouse.floors = recommendation.getInputInteger();
				break;
			case 5:
				System.out.println("What is the asking price?");
				desiredHouse.price = recommendation.getInputInteger();
				break;
			case 6:
				System.out.println("What is the living area (in sqr meters)?");
				desiredHouse.livingArea = recommendation.getInputInteger();
				break;
			case 7:
				System.out.println("What is the energy label of your house?");
				desiredHouse.energyLabel = recommendation.getInputString();
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
		
		//Calculate variances/squared errors (of individual instance attribute)
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
		
		//Calculate standard deviation (of all instances) 
		double priceSD =0;
		double livingAreaSD = 0;
		for (int i=0; i < houses.length; i++) {
			priceSD += houses[i].price;
			livingAreaSD = houses[i].livingArea;
		}
		priceSD = Math.sqrt(priceSD / houses.length);
		livingAreaSD = Math.sqrt(livingAreaSD / houses.length);
		
		
		// Manhattan Distance
		// The code below calculates the total manhattan distance in variable sum. 
		// 		This value is saved in a 2d array. Afterwards, the 2d array is sorted
		//		creating the desired sorted houseId array. To print the recommended houses
		//		this method uses the 2d recommendedhouses array and prints to top n houses. 
		double[][] recommendedHouses = new double[houses.length][2];
		for (int i=0; i< houses.length;i++) {
			// house id in first element
			house h = houses[i];
			
			// distance metric in first element
			double sum = 0;
			sum = desiredHouse.hasGarden == h.hasGarden ? sum ++ : sum;
			sum += Math.abs(desiredHouse.noOfRooms - h.noOfRooms);
			sum += Math.abs(desiredHouse.noOfBathrooms - h.noOfBathrooms);
			sum += Math.abs(desiredHouse.noOfBedrooms - h.noOfBedrooms);
			sum += Math.abs(desiredHouse.floors - h.floors);
			sum += Math.abs((desiredHouse.price - meanPrice) / priceSD);
			sum += Math.abs((desiredHouse.livingArea - meanlivingArea) / livingAreaSD);
			recommendedHouses[i][0] = sum;

			// house ID in second element
			recommendedHouses[i][1] = houses[i].houseId;
		}
		Arrays.sort(recommendedHouses, (a, b) -> Double.compare(a[0], b[0]));
		
		for (int i=0; i < 5; i++) {
			for (int j=0; j < houses.length; j++) {
				if (houses[j].houseId == ((int)recommendedHouses[i][1])) {
					house h = houses[j];
					System.out.println("-------------------------------------------------\n"
							+ "House " + i + ".");
					System.out.println(" * House ID: " + (int) recommendedHouses[i][1]);
					System.out.println(" * Address: " + h.address);
					System.out.println(" * Number of rooms: " + h.noOfRooms);
					System.out.println(" * Number of bathrooms: " + h.noOfBathrooms);
					System.out.println(" * Number of bedrooms: " + h.noOfBedrooms);
					System.out.println(" * Garden (yes/no): " + h.hasGarden);
					System.out.println(" * Energy label: " + h.energyLabel);
					System.out.println(" * Number of floors: " + h.floors);
					System.out.println(" * Living area: " + h.livingArea);
					System.out.println(" * Price: " + h.price);
				}
			}
		}
		
		if (recommendation.currentBuyer != null) {
			System.out.println("Would you like to view a house page? \n"
					+ "Enter the corresponding number (-1 to exit)");
			int viewChoice = recommendation.getInputInteger();
			System.out.println("*****************************************************");
			if (viewChoice > 5) {
				// The user specified a non existing number
				System.out.println("Specified number not available\nPlease fill in a new number:");
				viewChoice = recommendation.getInputInteger();
			}else if(viewChoice == -1) {
				// nothing happens and program continues
			}else {
				recommendation.currentBuyer.viewHousePage( (int) recommendedHouses[viewChoice][1]);
			}}
		
	}
	

	//this methods reads all accounts and checks is anything matches
		public static void login(){
			// Retreive username and password to log into account
			System.out.println("Enter a username:");
			String username = recommendation.getInputString();
			System.out.println("Enter a password:");
			String password = recommendation.getInputString();
			
			// helper variables to read data from files
			String sCurrentLine;
			String[] uCurrent;
			String type;
			try {
				BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
				
				//If the username password combination is not found, logging in is failed
				boolean fail = true;
				while((sCurrentLine = br.readLine()) != null) {
					uCurrent = sCurrentLine.split(";");
					
					if (username.equals(uCurrent[1]) && password.equals(uCurrent[2])) {
						System.out.println("You've succesfully logged in as: "+ username);
						type = uCurrent[3];
						// Depending on the type of the account, the user will be logged in as corresponding account type
						if (type.equals("buyer")) {
							recommendation.currentBuyer = new buyer(Integer.parseInt(uCurrent[0]), uCurrent[1]);
						} else if (type.equals("seller")) {
							recommendation.currentSeller = new seller(Integer.parseInt(uCurrent[0]), uCurrent[1]);
						} else if(type.equals("admin")) {
							recommendation.admin = new admin();
						} else {
							System.out.println("An error appeared setting the user subclass");
						}
						fail = false;
					} 
				}
				if (fail  ==true){
					System.out.println("could not log in. /nSorry :'(");
					System.out.println("Would you like to try again? (y/n)" );
					String again = recommendation.getInputString();
					if (again == "y");login();
				}
				br.close();
			}catch(IOException e){
				System.out.println("Could not log in");
			}
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
						String type = uCurrent[3];
						// Depending on the type of the account, the user will be logged in as corresponding account type
						if (type == "buyer") {
							u = new buyer(Integer.parseInt(uCurrent[0]), uCurrent[1]);
						} else if (type == "seller") {
							u = new seller(Integer.parseInt(uCurrent[0]), uCurrent[1]);
						} else if(type == "admin") {
							u = new admin();
						} else {
							System.out.println("Error reading account");
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


