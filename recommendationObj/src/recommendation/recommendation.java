/*
 * This program is a command line controlled recommendation system.
 * It consists of three parts: loading, logging in and the main functionality. 
 * A user in the most general sense can view all houses. When the user has created an 
 * 		account, s/he can utilize the extra functionality. For example, view a house. 
 */



package recommendation;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
public class recommendation {
	static Scanner inputStr = new Scanner(System.in);
	static Scanner inputInt = new Scanner(System.in);
	static Scanner inputBool = new Scanner(System.in);
	static Scanner inputDbl = new Scanner(System.in);
	static int houseCounter; //these counters keep track of the number of objects and are used to set unique ID's
	static int biddingCounter;
	static int userCounter;
	
	static user currentUser; 
	// Only one of the user subclasses will be initialized in each run of the program.
	//		if user logs into a buyer account, the currentBuyer attribute will be set.
	static buyer currentBuyer; 
	static seller currentSeller;
	static admin admin;
	
	// Program starts here
	public static void main(String[] args) {
		
		// 1. start program
		// *loads general info
		 generalReader();
		 
		// 2. ask login
		System.out.println("Would you like to login? (y/n). Or create a new account (new)");
		String login = getInputString();
		if (login.equals("y")) {
			System.out.println("logging in");
			user.login();
		}else if(login.equals("new")) {
			// If the user wants to create a new account s/he chooses new
			createAccount();

			// After creating the account, the user will need to log in
			System.out.println("Please fill in your username and password to login");
			user.login();
		}
		
		boolean active = true;
		while(active == true) {
		// 3.use program
		System.out.println("Which of the following would you like to do? ");
		if (currentBuyer != null) {
			System.out.println("* View the houses (1)");
			System.out.println("* Place a bid on a house (2)");
			System.out.println("* View saved house list (3)");
			System.out.println("* Exit program (-1)");
			System.out.println("Enter activity number here:");
			int userChoice = inputInt.nextInt();
			switch (userChoice) {
			case 1:
				// Choosing this case, the program shows all houses (after specifying criteria)
				// 		After viewing, the buyer can select a home and (also) optionally place a bid or
				//		add the house to his/her saved list/
				currentBuyer.viewHouseList(); 
				break;
			case 2:
				// Choosing this case, the user can place a bidding on a house. Remembering the houseId
				//		is required.
				System.out.println("On which house do you want to place a bidding? \n"
						+ "Fill in a house ID number here:");
				int houseId = getInputInteger();
				currentBuyer.createBidding(houseId);
				break;
			case 3:
				// Choosing this case, the program shows all houses saved by the buyer.
				currentBuyer.viewSavedList();
				break;
			case -1:
				active = false; 
				break;
			}
		} else if(currentSeller != null) {
			System.out.println("* add a house (1)");
			System.out.println("* view biddings on your house (2)");
			System.out.println("* determine bidding (3)");
			System.out.println("* edit your house (4)");
			System.out.println("* Exit program (-1)");
			System.out.println("Enter activity number here:");
			int userChoice = inputInt.nextInt();
			
			
			switch (userChoice) {
			case 1:
				// Choosing this option, the seller can add a house if s/he does not have one yet.
				
				// Initial check if the user does already have a house
				if (house.getHouseId(currentSeller.userId) == 0) {
					currentSeller.addHouse();
				}
				break;
			case 2:
				// Choosing this, the seller can view the biddings on the house
				bidding.viewBiddings(currentSeller.userId);
				break;
			case 3:
				// Choosing this, the seller can either accept of decline the bidding
				currentSeller.determineBidding(); 
				break;
			case 4:
				currentSeller.editHouse(); 
				break;
			case -1:
				active = false; 
				break;
			}
		} else if(admin != null) {
			
			// The admin can choose the options below.
			// Any missing functions will be updated on the next update 
			System.out.println("* remove a house (1)");
			System.out.println("* edit a house offering (2)");
			System.out.println("* remove a bidding (3)");
			System.out.println("* delete a user (4)");
			System.out.println("* Exit program (-1)");
			System.out.println("Enter activity number here:");
			int userChoice = inputInt.nextInt();
			switch (userChoice) {
			case 1:
				admin.removeHouse();
				break;
			case 2:
				admin.editHouse();
				break;
			case 3:
				admin.removeBidding(); 
				break;
			case 4:
				admin.deleteUser(); 
				break;
			case -1:
				active = false; 
				break;
			}
		} else { //reaching this means the user is a guest
			System.out.println("(You've started the program as a guest user)");
			user guest = new user();
			System.out.println("* View the houses (1)");
			System.out.println("* Exit program (-1)");
			System.out.println("Enter activity number here:");
			int userChoice = inputInt.nextInt();
			switch (userChoice) {
			case 1:
				guest.viewHouseList();
				break;
			case -1:
				active = false; 
				break;
			
		}
		}
		}
	}	
	
	
	//this method initializes the general program counters
	public static void updateProgram() {
		generalUpdate(houseCounter,biddingCounter,userCounter);
	}
	
	
	// creates an account and sets with right user sublass/type.
	// additionally, the user counter is updated 
	public static void createAccount() {
		// A user can choose whether s/he wants to be a buyer or seller
		System.out.println("Are you a buyer (b) or seller (s) ?");
		String type = inputStr.nextLine();
		System.out.println("Enter a username:");
		String username = inputStr.nextLine();
		System.out.println("Enter a password:");
		String password = inputStr.nextLine();
		if (type.equals("b")) {
			user.appendAccounts(userCounter,username,password,"buyer");
		}else {
			user.appendAccounts(userCounter, username, password, "seller");
		}
		//update the program that an extra user is added
		userCounter ++;
		generalUpdate(houseCounter, biddingCounter, userCounter);
		System.out.println("Succesfully created account");
	}
		

	
	
	
		//this method initializes the houseCounter, userCounter and biddingCounter in recommendation class
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
		
		// This method sets the counters in the database, in order to pick up on the next session
		public static void generalUpdate(int houseId, int biddingId,int userId){
			
			try {
				PrintWriter wr = new PrintWriter(new BufferedWriter(
								 new FileWriter("general.txt",false)));				
				wr.println(houseId +";"+ biddingId +";"+ userId);
				wr.close();
			}catch(IOException e) {
				System.out.println("File could not be read or appended to");
			}
		}
	
		
		//Below are four scanner methods, one for each input type.
		//The methods are used to directly validate the userinput,
		//	as alternative to validating in local code on each scanner call.
		public static int getInputInteger() {
			int inputUser;
			while(true) {
				try {
					inputUser = inputInt.nextInt();
					break;
				}
				catch(InputMismatchException ex) {
					System.out.println("That is not a valid integer input");
					inputInt.next();
				}
			}
			return inputUser;
		}
		
		public static String getInputString() {
			String inputUser;
			while(true) {
				try {
					inputUser = inputStr.nextLine();
					break;
				}
				catch(InputMismatchException ex) {
					System.out.println("That is not a valid String input");
					inputStr.next();
				}
			}
			return inputUser; 
		}
		
		public static boolean getInputBool() {
			boolean inputUser;
			while(true) {
				try {
					inputUser = inputBool.nextBoolean();
					break;
				}
				catch(InputMismatchException ex) {
					System.out.println("That is not a valid boolean input\nPlease fill in a boolean value");
					inputBool.next();
				}
			}
			return inputUser; 
		}
		
		public static double getInputDbl() {
			double inputUser;
			while(true) {
				try {
					inputUser = inputDbl.nextDouble();
					break;
				}
				catch(InputMismatchException ex) {
					System.out.println("That is not a valid double input");
					inputDbl.next();
				}
			}
			return inputUser; 
		}
		
	
}
	
	
	
	