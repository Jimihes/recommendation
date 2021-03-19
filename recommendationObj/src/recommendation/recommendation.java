/*
 * This program is a command line controlled recommendation system.
 * 
 * 
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
	static int houseCounter; //might decide to move these counters to the corresponding class later
	static int biddingCounter;
	static int userCounter;
	static user currentUser;
	static buyer currentBuyer;
	static seller currentSeller;
	static admin admin;
	
	public static void main(String[] args) {
		
		// 1. start program
		// *loads general info
		 loadProgram();
		 System.out.println("users" + userCounter + "houses" + houseCounter + "users: " + userCounter);
		
		 
		// 2. ask login
		System.out.println("Would you like to login? (y/n). Or create a new account (new)");
		String login = getInputString();
		if (login.equals("y")) {
			System.out.println("logging in");
			user.login();
		}else if(login.equals("new")) {
			createAccount();
			System.out.println("Please fill in your username and password to login");
			user.login();
		}else { 
			// user will be quest
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
				currentBuyer.viewHouseList(); //Not finished yet !!
				break;
			case 2:
				System.out.println("On which house do you want to place a bidding? \n"
						+ "Fill in a house ID number here:");
				int houseId = getInputInteger();
				currentBuyer.createBidding(houseId);
				break;
			case 3:
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
				currentSeller.addHouse();
				break;
			case 2:
				bidding.viewBiddings(currentSeller.userId);
				break;
			case 3:
				currentSeller.determineBidding(); 
				//if sold: automatically delete this house (not a user choice)
				break;
			case 4:
				currentSeller.editHouse(); 
				//if sold: automatically delete this house (not a user choice)
				break;
			case -1:
				active = false; 
				break;
			}
		} else if(admin != null) {
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
	
	// this method loads the general program counters
	public static void loadProgram() {
		generalReader();
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
			if (type.equals("b")) {
				user.appendAccounts(1,username,password,"buyer");
			}else {
				user.appendAccounts(1, username, password, "seller");
			}
			//update the program that an extra user is added
			recommendation.userCounter ++;
			generalUpdate(houseCounter, biddingCounter, userCounter);
			System.out.println("Succesfully created account");
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
					inputBool.next();
				}
			}
			return inputUser; 
		}
		
	
}
	
	
	
	