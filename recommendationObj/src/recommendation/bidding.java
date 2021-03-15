package recommendation;

import java.util.ArrayList;

public class bidding {
	double total; //the total amount of the offer 
	int bidId;
	int userId; //the user who placed the bid
	int houseId; //the house on which this bid is placed
	String status = "undefined"; // the seller can set this to accepted or declined
	
	public bidding(int biddingId,double price, int userId, int houseId) {
		total = price;
		bidId = recommendation.biddingCounter;
		this.userId = userId;
		houseId = 2;
		
	}
	
	public static void viewBiddings (int userId) {
		int houseId = house.getHouseId(userId);
		bidding[] x = databaseReader.biddingsReader();
		ArrayList<bidding> biddings = new ArrayList<bidding>();
		for (int i = 0; i<x.length; i++) {
			if (x[i].houseId == houseId) {
				biddings.add(x[i]);
			}
		}
		System.out.println("Bid ID" +"\t" + "Total" + "\t" + "User ID");
		for (int i = 0; i<biddings.size(); i++) {
			System.out.println(biddings.get(i).bidId +"\t" + biddings.get(i).total +"\t" + biddings.get(i).userId);
		}
	}
}