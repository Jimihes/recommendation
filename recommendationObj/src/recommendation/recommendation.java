/*
 * This program is a command line controlled recommendation system.
 * 
 * 
 */



package recommendation;
import java.util.Scanner;
import java.io.*;
public class recommendation {
	static Scanner inputStr = new Scanner(System.in);
	static Scanner inputInt = new Scanner(System.in);
	static int houseCounter; //might decide to move these counters to the corresponding class later
	static int biddingCounter;
	static int userCounter;
	static user currentUser;
	static user.buyer currentBuyer;
	static user.seller currentSeller;
	static user.admin admin;
	
	public static void main(String[] args) {
		
		// 1. start program
		// *loads general info
		 loadProgram();
		 System.out.println("users" + userCounter + "houses" + houseCounter + "users: " + userCounter);
		
		 
		// 2. ask login
		System.out.println("Would you like to login? (y/n). Or create a new account (new)");
		String login = inputStr.nextLine();
		if (login.equals("y")) {
			System.out.println("logging in");
			login();
		}else if(login.equals("new")) {
			createAccount();
			login();
		}else { 
			// user will be quest
		}
		
		
		// 3.use program
		System.out.println("Which of the following would you like to do? ");
		if (currentBuyer != null) {
			System.out.println("* View the houses (1)");
			System.out.println("* Place a bid on a house (2)");
			System.out.println("* View saved house list (3)");
			System.out.println("Enter activity number here:");
			int userChoice = inputInt.nextInt();
			switch (userChoice) {
			case 1:
				//viewHouselist();
				break;
			case 2:
				currentBuyer.createBidding();
				break;
			case 3:
				//viewSavedList();
				break;
			}
		} else if(currentSeller != null) {
			System.out.println("* add a house (1)");
			System.out.println("* view biddings on your house (2)");
			System.out.println("* determine bidding (3)");
			System.out.println("Enter activity number here:");
			int userChoice = inputInt.nextInt();
			switch (userChoice) {
			case 1:
				currentSeller.addHouse();
				break;
			case 2:
				//viewBiddings(); johann
				break;
			case 3:
				//determineBidding(); eva
				//if sold: automatically delete this house (not a user choice)
				break;
			}
		} else if(admin != null) {
			System.out.println("* remove a house (1)");
			System.out.println("* edit a house offering (2)");
			System.out.println("* remove a bidding (3)");
			System.out.println("* delete a user (4)");
			System.out.println("Enter activity number here:");
			int userChoice = inputInt.nextInt();
			switch (userChoice) {
			case 1:
				//removeHouse();
				break;
			case 2:
				//editHouse();
				break;
			case 3:
				//removeBidding(); sarah
				break;
			case 4:
				//deleteUser(); lawrence
				break;
			}
		} else { //reaching this means the user is a guest
			
		}
		
		

	}	
	
	
	public static void login() {
		System.out.println("Enter a username:");
		String username = inputStr.nextLine();
		System.out.println("Enter a password:");
		String password = inputStr.nextLine();
		String type = databaseReader.accountsReader(username, password);
		currentUser = new user();
		if (type.equals("buyer")) {
			currentBuyer = currentUser.new buyer();
			currentBuyer.password = password;
			currentBuyer.username = username;
		} else if (type.equals("seller")) {
			currentSeller = currentUser.new seller();
			currentSeller.password = password;
			currentSeller.username = username;
		} else if(type.equals("admin")) {
			admin = currentUser.new admin();
			admin.username = username;
			admin.password = password;
		} else {
			System.out.println("could not log in. /nSorry :'(");
		}
	}
	
	//this method initializes the general program counters
	public static void updateProgram() {
		databaseWriter.generalUpdate(houseCounter,biddingCounter,userCounter);
	}
	
	// this method loads the general program counters
	public static void loadProgram() {
		databaseReader.generalReader();
	}
	
	
		// creates an account and sets with right type.
		// additionally, the user counter is updated 
		public static void createAccount() {
			System.out.println("Are you a buyer (b) or seller (s) ?");
			String type = inputStr.nextLine();
			System.out.println("Enter a username:");
			String username = inputStr.nextLine();
			System.out.println("Enter a password:");
			String password = inputStr.nextLine();
			if (type == "b") {
				databaseWriter.appendAccounts(1,username,password,"buyer");
			}else {
				databaseWriter.appendAccounts(1, username, password, "seller");
			}
			//update the program that an extra user is added
			recommendation.userCounter ++;
			databaseWriter.generalUpdate(houseCounter, biddingCounter, userCounter);
		}
		

	
	
	

	
	
	
}
	
	
	
	