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
				currentBuyer.viewHouseList(); //Not finished yet !!
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
				bidding.viewBiddings(currentSeller.userId);
				break;
			case 3:
				currentSeller.determineBidding(); 
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
				admin.removeBidding(); 
				break;
			case 4:
				admin.deleteUser(); 
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
		String type = user.accountsReader(username, password);
		currentUser = new user();
		if (type.equals("buyer")) {
			currentBuyer = new buyer();
			currentBuyer.password = password;
			currentBuyer.username = username;
		} else if (type.equals("seller")) {
			currentSeller = new seller();
			currentSeller.password = password;
			currentSeller.username = username;
		} else if(type.equals("admin")) {
			admin = new admin();
			admin.username = username;
			admin.password = password;
		} else {
			System.out.println("could not log in. /nSorry :'(");
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
			if (type == "b") {
				user.appendAccounts(1,username,password,"buyer");
			}else {
				user.appendAccounts(1, username, password, "seller");
			}
			//update the program that an extra user is added
			recommendation.userCounter ++;
			generalUpdate(houseCounter, biddingCounter, userCounter);
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
								 new FileWriter("general.txt",true)));				
				wr.println(houseId +";"+ biddingId +";"+ userId);
				wr.close();
			}catch(IOException e) {
				System.out.println("File could not be read or appended to");
			}
		}
	
	
	
}
	
	
	
	