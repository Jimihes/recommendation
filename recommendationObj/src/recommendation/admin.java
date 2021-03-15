package recommendation;
import java.util.*;

public class admin extends user{
		
	//Attributes of the class:
	static Scanner InputInt = new Scanner(System.in);
	static Scanner InputString = new Scanner(System.in);
	static Scanner InputDouble = new Scanner(System.in);
	
		private void removeHouse(house h) {
			System.out.println(h.address);
		}
		private void editHouse(house h) {
			System.out.println(h.address);		
		}
		
	
	
	
	//Method of the class:
    public void removeBidding() {
	    System.out.println("Please fill in the following information about the bid you want to remove: ");
		System.out.println("Bidding id: ");	
		int bidIdInput = InputInt.nextInt(); 
		
		
		bidding[] oldbiddings = bidding.biddingsReader();
	
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
		bidding.overwriteBiddingsFile(newbidding);
    }
    
    public void deleteUser() {
    	
		//deleteUser(); 
		System.out.println("Enter the ID of the user you would like to remove: ");
		int userToBeRemoved = InputInt.nextInt();
		user[] users = user.userReader();
		user[] newusers = new user[users.length-1];
		
		for (int i=0; i < users.length;i++) {
			if (userToBeRemoved == users[i].userId) {
				System.out.printf("User %d has been removed successfully\n", users[i].userId);
				continue;
			}
			newusers[i] = users[i];
			break;
			}
		user.overwriteUsersFile(newusers);
		}
    
}
