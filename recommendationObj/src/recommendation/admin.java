package recommendation;
import java.util.*;

public class admin extends user{
		
	//Attributes of the class:
	static Scanner InputInt = new Scanner(System.in);
	static Scanner InputString = new Scanner(System.in);
	static Scanner InputDouble = new Scanner(System.in);
	
	public void removeHouse() {
		//retreive houseId to be removed
		System.out.println("Which house do you want to remove? Enter an integer");
		int houseId = recommendation.getInputInteger();

		house[] houseList = house.housesReader();
		house[] newHouseList = new house[houseList.length -1];
		
		house yourHouse = new house();
		for (int i = 0; i < houseList.length; i++) {
			if(houseList[i].houseId == houseId) {
				yourHouse = houseList[i];
			}
		}
		System.out.println("Is this the house you want to remove? (y/n)");
		System.out.println("Address: " + yourHouse.address);
		String login = recommendation.getInputString();
		if (login.equals("y")) {
			int newIndex = 0;
			for (int i = 0; i <houseList.length; i++){
				if (houseList[i].houseId == houseId) {
					System.out.printf("The house with the address " + yourHouse.address +  " has been removed successfully");
					continue;
				}
				newHouseList[newIndex] = houseList[i];
				newIndex++;
			}
			house.overwriteHousesFile(newHouseList);
		}else {
			System.out.println("did not remove house");
			return;
		}
	}
	
		//this method allows the seller to edit information about the house 
		public static void editHouse() {
			int editHouseID;
			
			house [] houseList = house.housesReader();
			
			
			//This method can be called by a seller. If so, the sellers house will be edited.
			if (recommendation.currentSeller != null) {
				editHouseID = house.getHouseId(recommendation.currentSeller.userId);
			}else {
				System.out.println("Please enter your house ID: "); 
				editHouseID = recommendation.getInputInteger();
			}

			System.out.println("Please fill in what house characteristic you want to change: "); 
			System.out.println("1 = presence of Garden"); 
			System.out.println("2 = number of rooms"); 
			System.out.println("3 = number of bathrooms"); 
			System.out.println("4 = number of bedrooms"); 
			System.out.println("5 = number of floors");
			System.out.println("6 = living area"); 
			System.out.println("7 = adress"); 
			System.out.println("8 = energy label"); 
			
			int sellerChoice = recommendation.getInputInteger(); 
			
			for (int i = 0; i < houseList.length; i++) {
				if (houseList[i].houseId == editHouseID) { 
					house h = houseList[i];
				
			
			switch (sellerChoice) {
			case 1: //hasGarden
				System.out.println("Does your house have a garden? (true/false)"); 
				boolean edithasGarden = recommendation.getInputBool(); 
			
				h.hasGarden = edithasGarden; 
				System.out.printf("Presence of a garden is changed from %d to %d successfully.", h.hasGarden, edithasGarden); 
				break; 
			
			case 2: //number of rooms 
				System.out.println("How many rooms has your house?");
				int editnoOfRooms = recommendation.getInputInteger(); 
				
				h.noOfRooms = editnoOfRooms; 
				System.out.printf("Number of rooms is changed from %d to %d successfully.", h.noOfRooms, editnoOfRooms); 
				break;
				
			case 3: //number of bathrooms 
				System.out.println("How many bathrooms has your house?"); 
				int editnoOfBathrooms = recommendation.getInputInteger(); 
				
				h.noOfBathrooms = editnoOfBathrooms; 
				System.out.printf("Number of bathrooms is changed from %d to %d successfully.", h.noOfBathrooms, editnoOfBathrooms); 
				break;
				
			case 4: //number of bedrooms 
				System.out.println("How many bedrooms has your house?"); 
				int editnoOfBedrooms = recommendation.getInputInteger(); 
				
				h.noOfBedrooms = editnoOfBedrooms; 
				System.out.printf("Number of bedrooms is changed from %d to %d successfully.", h.noOfBedrooms, editnoOfBedrooms); 
				break; 
				
			case 5: //number of floors 
				System.out.println("How many floors has your house?");
				int editFloors = recommendation.getInputInteger(); 
				
				h.floors = editFloors; 
				System.out.printf("Number of floors is changed from %d to %d successfully.", h.floors, editFloors); 
				break; 
				
			case 6: //living area 
				System.out.println("What is the living area (in sqr meters)?"); 
				int editLivingArea = recommendation.getInputInteger(); 
				
				h.livingArea = editLivingArea; 
				System.out.printf("Living area is successfully changed from %d to %d.", h.livingArea, editLivingArea); 
				break; 
				
			case 7: //address
				System.out.println("What is the address of your house?"); 
				String editAddress = recommendation.getInputString();
				
				h.address = editAddress; 
				System.out.printf("Address is successfully changed from %s to $s.", h.address, editAddress);
				break; 
				
			case 8: //energy label 
				System.out.println("What is the energy label of your house?"); 
				String editEnergyLabel = recommendation.getInputString(); 
				
				h.energyLabel = editEnergyLabel; 
				System.out.printf("Energy label is successfully changed from %s to %s.", h.energyLabel, editEnergyLabel); 
				break; 
			}
				house.overwriteHousesFile(houseList); 
				}
			}
		}
		
		
	
	
	
	//Method of the class:
    public void removeBidding() {
	    System.out.println("Please fill in the following information about the bid you want to remove: ");
		System.out.println("Bidding id: ");	
		int bidIdInput = InputInt.nextInt(); 
		
		
		bidding[] oldbiddings = bidding.biddingsReader();
	
		bidding[] newbidding = new bidding[oldbiddings.length-1];
		
		int newIndex = 0;
		for (int i = 0; i < oldbiddings.length; i++){
			//if statement to not add removable bid to newbidding array
			if (oldbiddings[i].bidId == bidIdInput) {
				System.out.printf("Bidding " + bidIdInput +  " has been removed successfully");
				continue;
			}
			newbidding[newIndex] = oldbiddings[i];
			newIndex++;
		}
		bidding.overwriteBiddingsFile(newbidding);
		System.out.println("The bidding is removed succesfully");
    }
    
    public void deleteUser() {
    	
		//deleteUser(); 
		System.out.println("Enter the ID of the user you would like to remove: ");
		int userToBeRemoved = InputInt.nextInt();
		user[] users = user.userReader();
		user[] newusers = new user[users.length-1];
		
		for (int i=0; i < users.length;i++) {
			if (userToBeRemoved == users[i].userId) {
				System.out.printf("User %d has been removed successfully\n", users[i].userId);
				continue;
			}
			newusers[i] = users[i];
			break;
			}
		user.overwriteUsersFile(newusers);
		}
    
}
