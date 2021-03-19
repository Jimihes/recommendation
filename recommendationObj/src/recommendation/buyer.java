package recommendation;

public class buyer extends user {
		
		public buyer( int userId, String username) {
			this.userId = userId;
			this.username = username;
		}
	
		
		public void createBidding(int houseId){
			//ask for the bidding total
			System.out.println("What do u wanna bid?");
			double offer = recommendation.getInputDbl();
			bidding bid = new bidding(recommendation.biddingCounter +1, offer, this.userId, houseId);
			
			//add the new bidding
			bidding.appendBiddings(bid);
			
			//update the biddingcounter with the extra bidding
			recommendation.biddingCounter ++;
			recommendation.generalUpdate(recommendation.houseCounter, 
											 recommendation.biddingCounter, 
											 recommendation.userCounter);
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
			System.out.println(biddings[0].houseId);
			
			int counter = 0;
			for (int i = 0; i < biddings.length;i++) {
				if (biddings[i].houseId == h.houseId) {
					counter ++;
					System.out.println(" * Bidding " + counter+ " :" + biddings[i].total);
				}
			}
			System.out.println("Do you want to place a bidding on this house? (y/n)");
			String bid = recommendation.getInputString();
			if (bid == "y") {
				recommendation.currentBuyer.createBidding(h.houseId);
			}
		
		}
	}

