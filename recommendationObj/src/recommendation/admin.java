package recommendation;
import java.util.*;
public class admin {
	//Attributes of the class:
	static Scanner userInputInt = new Scanner(System.in);
	static Scanner userInputString = new Scanner(System.in);
	static Scanner userInputDouble = new Scanner(System.in);
	
	//Method of the class:
    public static void removeBidding() {
	    System.out.println("Please fill in the following information about the bid you want to remove: ");
		System.out.println("Bidding id: ");	
		int bidIdInput = userInputInt.nextInt(); 
		
		
		bidding[] oldbiddings = databaseReader.biddingsReader();
	
		bidding[] newbidding = new bidding[oldbiddings.length-1];
		
		int newIndex = 0;
		for (int i = 0; i < oldbiddings.length; i++){
			//if statement to not add removable bid to newbidding array
			if (oldbiddings[i].bidId == bidIdInput) {
				System.out.printf("Bidding " + bidIdInput +  " has been removed successfully");
				continue;
			}
			newbidding[newIndex] = oldbiddings[i];
			newIndex++;
		}
		databaseWriter.overwriteBiddingsFile(newbidding);
    }

}
