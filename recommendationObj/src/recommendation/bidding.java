package recommendation;


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
}