package recommendation;

import java.io.BufferedWriter;
import java.io.*;
public class databaseWriter {
	
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
	
	public static void appendAccounts(int id,String username, String password, String type){
		try {
			PrintWriter wr = new PrintWriter(new BufferedWriter(
							 new FileWriter("accounts.txt",true)));				
			wr.println(id +";"+ username +";"+ password +";"+ type);
			wr.close();
		}catch(IOException e) {
			System.out.println("File could not be read or appended to");
		}
	}
	
	public static void overwriteUsersFile(user[] users) {
		try {
			PrintWriter wr = new PrintWriter(new BufferedWriter(
							 new FileWriter("biddings.txt",false)));				
			for (int i = 0; i < users.length; i++) {
				user u = users[i];
				if (u instanceof user.buyer) wr.println(u.username +";"+ u.password + ";" + u.userId +";buyer");
				else if (u instanceof user.seller) wr.println(u.username +";"+ u.password + ";" + u.userId +";seller");
				else if (u instanceof user.admin) wr.println(u.username +";"+ u.password + ";" + u.userId +";admin");
			}
			wr.close();
			
		}catch(IOException e) {
			System.out.println("File could not be overwritten");
		}
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
	
	public static void appendHouse(house h) {
		try {
			PrintWriter wr = new PrintWriter(new BufferedWriter(
							 new FileWriter("houses.txt",true)));				
			wr.println(h.hasGarden + ";" + h.noOfRooms + ";" + h.noOfBathrooms +";" + h.noOfBedrooms + ";" + h.floors + ";" 
							 + h.price + ";" + h.livingArea + ";" + h.address + ";" + h.energyLabel+ ";" + h.houseId + ";" 
							 + recommendation.currentSeller.userId);
			wr.close();
		}catch(IOException e) {
			System.out.println("File could not be read or appended to");
		}
	}
	public static void overwriteHousesFile(house[] houses) {
		try {
			PrintWriter wr = new PrintWriter(new BufferedWriter(
							 new FileWriter("houses.txt",false)));				
			for (int i = 0; i < houses.length; i++) {
				house h = houses[i];
				wr.println(h.hasGarden + ";" + h.noOfRooms + ";" + h.noOfBathrooms +";" + h.noOfBedrooms + ";" + h.floors + ";" 
						 + h.price + ";" + h.livingArea + ";" + h.address + ";" + h.energyLabel+ ";" + h.houseId + ";" 
						 + recommendation.currentSeller.userId);
			}
			wr.close();
			
		}catch(IOException e) {
			System.out.println("File could not be read or appended to");
		}
	
		
	}

}
