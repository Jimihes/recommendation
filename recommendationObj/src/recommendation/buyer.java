package recommendation;

public class buyer extends user {
		
		
		public void createBidding(){
			//ask for the bidding total
			System.out.println("What do u wanna bid?");
			double offer = inputDbl.nextDouble();
			bidding bid = new bidding(recommendation.biddingCounter +1, offer, this.userId, 99);
			
			//add the new bidding
			bidding.appendBiddings(bid);
			
			//update the biddingcounter with the extra bidding
			recommendation.biddingCounter ++;
			recommendation.generalUpdate(recommendation.houseCounter, 
											 recommendation.biddingCounter, 
											 recommendation.userCounter);
		}
		
		public void viewHouseList(){
			// code to ask criteria
			
			
			// code to view all houses
			
			// if user wants to view single house:
			house h= new house();
			viewHousePage(h);
		}
		
		public void viewHousePage(house h) {	// I think this belongs to the class house 
			System.out.println("Address: " + h.address);
			System.out.println("Number of rooms: " + h.noOfRooms);
			System.out.println("Number of bathrooms: " + h.noOfBathrooms);
			System.out.println("Number of bedrooms: " + h.noOfBedrooms);
			System.out.println("Garden (yes/no): " + h.hasGarden);
			System.out.println("Energy label: " + h.energyLabel);
			System.out.println("Number of floors: " + h.floors);
			System.out.println("Living area: " + h.livingArea);
			System.out.println("Price: " + h.price);
			
		
		}
	}

