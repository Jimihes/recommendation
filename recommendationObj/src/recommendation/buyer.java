package recommendation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class buyer extends user {
		
		public buyer() {
		}
		
		//This method allows the buyer to view his/her saved houses list
		public void viewSavedList() {
			System.out.println("----------------------------------\n"
					+ "The following houses are in your favorite list");
			try {
				BufferedReader br = new BufferedReader(new FileReader("favouritelist.txt"));
				String sCurrentLine;
				String[] uCurrent;
				int favCounter=0;
				while((sCurrentLine = br.readLine()) != null) {
					uCurrent = sCurrentLine.split(";");
					if (Integer.parseInt(uCurrent[0]) == this.userId) {
						favCounter++;
						int houseId = Integer.parseInt(uCurrent[0]);
						house h = house.houseReader(houseId);
						System.out.println(" * Address:" + h.address + ", house ID: " +h.houseId);
						
					}
				}
				br.close();
				if (favCounter == 0) {
					System.out.println("You have no favorite homes");
					System.out.println("------------------------------------");
				}
			} catch(IOException e){
				System.out.println("Could not read savedList;");
			}

		}
		
		
		public void createBidding(int houseId){
			
			house h = house.houseReader(houseId);
			if (h.houseId != -1) {

			//ask for the bidding total
			System.out.println("What do you want to bid?");
			double offer = recommendation.getInputDbl();
			bidding bid = new bidding(recommendation.biddingCounter +1, offer, this.userId, houseId);
			
			//add the new bidding
			bidding.appendBiddings(bid);
			
			//update the biddingcounter with the extra bidding
			recommendation.biddingCounter ++;
			recommendation.generalUpdate(recommendation.houseCounter, 
											 recommendation.biddingCounter, 
											 recommendation.userCounter);
		}else System.out.println("Did not find a house with that house id");
		}
		
		public void viewHousePage(int houseId) {	// I think this belongs to the class house
			
			house h = house.houseReader(houseId);
			
			// Printing house attributes
			System.out.println("Address: " + h.address + "\n"+
				"Number of rooms: " + h.noOfRooms+ "\n"+
				"Number of bathrooms: " + h.noOfBathrooms+ "\n"+
				"Number of bedrooms: " + h.noOfBedrooms+ "\n"+
				"Garden (yes/no): " + h.hasGarden+ "\n"+
				"Energy label: " + h.energyLabel+ "\n"+
				"Number of floors: " + h.floors+ "\n"+
				"Living area: " + h.livingArea+ "\n"+
				"Price: " + h.price+ "\n"+
				"--------------------------------------------------------");
			System.out.println("The current biddings on this house are:");
			bidding[] biddings = bidding.biddingsReader();
			
			int counter = 0;
			for (int i = 0; i < biddings.length;i++) {
				if (biddings[i].houseId == h.houseId) {
					counter ++;
					System.out.println(" * Bidding " + counter+ " :" + biddings[i].total);
				}
			}
			System.out.println("Do you want to add this house to your favorite list? (y/n)");
			String favorite = recommendation.getInputString();
			if (favorite.equals( "y")) {
				try {
					PrintWriter wr = new PrintWriter(new BufferedWriter(
									 new FileWriter("favouritelist.txt",true)));				
					wr.println(recommendation.currentBuyer.userId + ";" + h.houseId);
					wr.close();
					System.out.println("The house was added to your favorites list");
				}catch(IOException e) {
					System.out.println("File could not be read or appended to");
				}
			}
			System.out.println("Do you want to place a bidding on this house? (y/n)");
			String bid = recommendation.getInputString();
			if (bid.equals("y")) {
				recommendation.currentBuyer.createBidding(h.houseId);
			}
		
		}
	}

