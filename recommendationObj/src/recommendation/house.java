package recommendation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class house {
	boolean hasGarden = false;
	int noOfRooms;
	int noOfBathrooms;
	int noOfBedrooms;
	int floors = 1;
	double price;
	double livingArea;
	String address;
	String energyLabel;
	int houseId;
	int sellerId;
	int acceptedBid;

	public house() {
		// this constructor is used to solve non return error
	}

	// test comment johann
	// test comment eva

	// third test comment

	public house(boolean hasGarden, int noOfRooms, int noOfBathrooms,int noOfBedrooms, int floors, double price, double livingArea,
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

	public static int getHouseId(int sellerId) {
		house[] x = housesReader();
		int id = 0;
		for (int i = 0; i < x.length; i++) {
			if (x[i].sellerId == sellerId) {
				id = x[i].houseId;
			}
		}
		return id;

	}
	//this method reads all house objects into an array
		public static house[] housesReader( ){
			String sCurrentLine;
			String[] uCurrent;
			house[] houses = new house[0];
			try {
				BufferedReader br = new BufferedReader(new FileReader("houses.txt"));
				int j=0;
				while((sCurrentLine = br.readLine()) != null) j++;
				houses = new house[j];
				int i = 0;
				while((sCurrentLine = br.readLine()) != null) {
					uCurrent = sCurrentLine.split(";");
					house h = new house(Boolean.parseBoolean(uCurrent[0]), Integer.parseInt(uCurrent[1]),
							Integer.parseInt(uCurrent[2]),Integer.parseInt(uCurrent[3]),Integer.parseInt(uCurrent[4]),
							Double.parseDouble(uCurrent[5]), Double.parseDouble(uCurrent[6]),
							uCurrent[7],uCurrent[8],Integer.parseInt(uCurrent[9]), 
							Integer.parseInt(uCurrent[10]), Integer.parseInt(uCurrent[11]));				
					houses[i] = h;
					i++;
				}
				br.close();
				
			}catch(IOException e){
				System.out.println("Could not read houses");
			}
			return houses;
		}
		//this method reads in a single house object
		public static house houseReader(int houseId) {
			String sCurrentLine;
			String[] uCurrent;
			house h = new house();
			try {
				BufferedReader br = new BufferedReader(new FileReader("houses.txt"));
				while((sCurrentLine = br.readLine()) != null) {
					uCurrent = sCurrentLine.split(";");
					if (Integer.parseInt(uCurrent[8]) == houseId) {
					h = new house(Boolean.parseBoolean(uCurrent[0]), Integer.parseInt(uCurrent[1]),
							Integer.parseInt(uCurrent[2]),Integer.parseInt(uCurrent[3]),Integer.parseInt(uCurrent[4]),
							Double.parseDouble(uCurrent[5]), Double.parseDouble(uCurrent[6]),
							uCurrent[7],uCurrent[8],Integer.parseInt(uCurrent[9]), 
							Integer.parseInt(uCurrent[10]), Integer.parseInt(uCurrent[11]));
				}}
				br.close();
				
			}catch(IOException e){
				System.out.println("Could not read houses");
			}
			return h;
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
