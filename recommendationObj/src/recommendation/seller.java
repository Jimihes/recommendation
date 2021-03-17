package recommendation;

import java.util.ArrayList;

public class seller extends user{
	
	public void addHouse() {
		boolean hasGarden = false;
		int noOfRooms = -1;
		int noOfBathrooms = -1;
		int noOfBedrooms = -1;
		int floors =1;
		double price = -1;
		double livingArea = -1;
		String address = "burgermeeste oudlaan 50";
		String energyLabel = "G";
		
		//list of the house attributes the user can specify
		String[] houseAttributes = {"available garden","Number of rooms", "Number of bathrooms", "Number of bedrooms", 
									"Number of floors", "Price", "Living area", "Address","Energy label","exit"};
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
				hasGarden = inputBool.nextBoolean();
				break;
			case 1:
				System.out.println("How many rooms does your house have?");
				noOfRooms = inputInt.nextInt();
				break;
			case 2:
				System.out.println("How many bathrooms does your house have?");
				noOfBathrooms = inputInt.nextInt();
				break;
			case 3:
				System.out.println("How many bathrooms does your house have?");
				noOfBathrooms = inputInt.nextInt();
				break;
			case 4:
				System.out.println("How many floors does your house have?");
				floors = inputInt.nextInt();
				break;
			case 5:
				System.out.println("What is the asking price?");
				price = inputInt.nextInt();
				break;
			case 6:
				System.out.println("What is the living area (in sqr meters)?");
				livingArea = inputInt.nextInt();
				break;
			case 7:
				System.out.println("What is the address?");
				address = inputStr.nextLine();
				break;
			case 8:
				System.out.println("What is the energy label of your house?");
				energyLabel = inputStr.nextLine();
				break;
			case 9:
				userChoice = -1;
				break;
			}
			if (userChoice != -1) atbs.remove(atbs.indexOf(userChoice));
		}
		house h = new house(hasGarden, noOfRooms, noOfBathrooms, noOfBedrooms, floors, price, livingArea, address,
							energyLabel, recommendation.houseCounter, recommendation.currentSeller.userId, -1);
		house.appendHouse(h);

		//update the program that an extra user is added
		recommendation.houseCounter ++;
		recommendation.generalUpdate(recommendation.houseCounter, 
				recommendation.biddingCounter, 
				recommendation.userCounter);
		
		
	}
	

	
	//this method determines the accepted bidding from the seller 
		public void determineBidding() {
				bidding[] biddings = bidding.biddingsReader(); //return an array of biddings from johann's method viewBiddings 
				house[] houseList = house.housesReader(); //return an array of house ID's from the viewhouseList 
				
				//is there a way that the system can read all this information automatically, instead of the user having to fill it in manually? 
				System.out.println("Please enter the following information about the bid you want to determine:"); 
				System.out.println("Bidding id: "); 
				int bidIdInput = inputInt.nextInt();  //enter bidding ID 
				
				System.out.println("Do you want to accept (true) or decline (false) this bidding?");
				boolean accepted = inputBool.nextBoolean();
				
				int houseId = house.getHouseId(recommendation.currentSeller.userId);
	
				
				// if the seller wants to accept the bidding, a house is sold and all biddings
				//		linked to that house will be removed from the biddingsarray
				if (accepted == true) {
				
				
				// create for loop with only biddings linked to other houses (arraylist)
				ArrayList<bidding> newbiddings = new ArrayList<bidding>(); 
				for (int i=0; i< biddings.length; i++) {
					if (biddings[i].houseId != houseId) {
						newbiddings.add(biddings[i]);
					}
				}
				// String[] array = list.toArray(new String[list.size()]);
				bidding[] arrbiddings = newbiddings.toArray(new bidding[newbiddings.size()]);
				bidding.overwriteBiddingsFile(arrbiddings); 
				
				
				// if the seller wants to decline the offer, only the attribute status of that
				//		bidding will be adjusted.
				} else {
					
					for (int i=0; i < biddings.length;i++) {
						// -change bidding.status to declined
						if ( biddings[i].houseId == houseId) {
							biddings[i].status = "declined";
						}
					}
					bidding.overwriteBiddingsFile(biddings);
				
				
				}
		
		
			}
		}
