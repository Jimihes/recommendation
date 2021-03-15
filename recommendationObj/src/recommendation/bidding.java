package recommendation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		bidding[] x = biddingsReader();
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
	// this method reads all biddings and returns them in an array
		public static bidding[] biddingsReader( ){
			String sCurrentLine;
			String[] uCurrent;
			bidding[] biddings = new bidding[0];
			try {
				BufferedReader br = new BufferedReader(new FileReader("biddings.txt"));
				int j=0;
				while((sCurrentLine = br.readLine()) != null) j++;
				biddings = new bidding[j];
				int i = 0;
				while((sCurrentLine = br.readLine()) != null) {
					uCurrent = sCurrentLine.split(";");
					bidding b = new bidding(Integer.parseInt(uCurrent[0]),Double.parseDouble(uCurrent[1]),Integer.parseInt(uCurrent[2]),
											Integer.parseInt(uCurrent[3]));
					biddings[i] = b;
					i++;
				}
				br.close();
				
			}catch(IOException e){
				System.out.println("Could not read houses");
			}
			return biddings;
		}

		public static void appendBiddings(bidding b){
			try {
				PrintWriter wr = new PrintWriter(new BufferedWriter(
								 new FileWriter("biddings.txt",true)));				
				wr.println(b.bidId +";"+ b.total + ";" + b.userId +";"+ b.houseId);
				wr.close();
			}catch(IOException e) {
				System.out.println("File could not be read or appended to");
			}
		  }
		
		
		public static void overwriteBiddingsFile(bidding[] biddings) {
			try {
				PrintWriter wr = new PrintWriter(new BufferedWriter(
								 new FileWriter("biddings.txt",false)));				
				for (int i = 0; i < biddings.length; i++) {
					bidding b = biddings[i];
					wr.println(b.bidId +";"+ b.total + ";" + b.userId +";"+ b.houseId);
				}
				wr.close();
				
			}catch(IOException e) {
				System.out.println("File could not be overwritten");
			}
		}
		
}