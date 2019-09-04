package files;
import java.util.Scanner;


public class MainConsole {


	public static void main(String[] args) {
		int choice;
		System.out.println("Welcome to MOBLIMA!");
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1) Customer");
			System.out.println("2) Staff");
			System.out.println("3) Exit");
			System.out.print("Please enter choice: ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1: 
				CustomerM1 customer = new CustomerM1();
				customer.show();
				break;
			case 2: 
				AdminM1 admin = new AdminM1();
				admin.show();
				break;
				
			case 3: System.out.println("Exiting..."); System.exit(0);
			}

		}while(choice != 3);
	}	
}
