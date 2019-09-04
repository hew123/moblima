package files;
import java.util.Scanner;


public class CustomerM1 {
	
	
	public void show() {
		int choice;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println();
			System.out.println("Customer");
			System.out.println("--------");
			System.out.println("1) Search/ List movies for showtimes and booking");
			System.out.println("2) View/ Give review and rating");
			System.out.println("3) View my booking history");
			System.out.println("4) List top 5 movies by sales/ ratings");
			System.out.println("5) Back");
			System.out.print("Please enter choice: ");
			choice = sc.nextInt();
			System.out.println();
			
			switch(choice) {
			
			case 1:
				SLmovie slmovie = new SLmovie();
				slmovie.show();
				break;
				
			case 2:
				ReviewRating reviewrating = new ReviewRating();
				reviewrating.show();
				break;
				
			case 3:
				Bookinghistory bookinghistory = new Bookinghistory();
				bookinghistory.show();
				break;
			
			case 4:
				Top5SR top5sr = new Top5SR();
				top5sr.show();
				break;
				
			default:
				break;
			}
		}while(choice != 5);
	}
}
