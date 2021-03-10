package recommendation;

public class house{
	boolean hasGarden = false;
	int noOfRooms;
	int noOfBathrooms;
	int floors =1;
	double price;
	double livingArea;
	String address;
	String energyLabel;
	int houseId;
	int sellerId;
	int acceptedBid;
	
	public house() {
		//this constructor is used to solve non return error
	}
	
	
	//test comment johann
	
	public house(boolean hasGarden, int noOfRooms, int noOfBathrooms, int floors, double price, double livingArea, 
				 String address, String energyLabel, int houseId, int sellerId, int acceptedBid) {
		this.hasGarden = hasGarden;
		this.noOfRooms = noOfRooms;
		this.noOfBathrooms = noOfBathrooms;
		this.floors = floors;
		this.price = price;
		this.livingArea = livingArea;
		this.address = address;
		this.energyLabel = energyLabel;
		this.houseId = houseId;
		this.sellerId = sellerId;
		this.acceptedBid = acceptedBid;
	}
	
}
