package files;
import java.util.Scanner;

public class AdminM1 {
	private String username = "user";
	private String password = "pass";
	
	public void show() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		boolean deny = true;
	
		do{
			System.out.print("Enter Username: ");
			String login = sc.nextLine();
	
			System.out.print("Enter Password: ");
			String pwd = sc.nextLine();
	
			if (username.equals(login) && password.equals(pwd)) {
				deny = false;
			}
			else {System.out.println("Login Invalid! Please try again.");}
		}while(deny);

		System.out.println();
		
		do {
			System.out.println();
			System.out.println("Admin Functions");
			System.out.println("---------------");
			System.out.println("1) Create/ Update/ Remove movie listing");
			System.out.println("2) Create/ Update/ Remove cinema showtimes and the movies to be shown");
			System.out.println("3) Configure system settings");
			System.out.println("4) Show top 5 movies by sales/ ratings");
			System.out.println("5) Back");
			System.out.print("Please enter choice: ");
			choice = sc.nextInt();
			System.out.println();

			switch(choice) {
			case 1:
				CURmovies CRUmovie = new CURmovies();
				CRUmovie.show();
				break;

			case 2:
				CURshowtimes CRUshowtime = new CURshowtimes();
				CRUshowtime.show();
				break;

			case 3:
				AdminSystemSetting adminsettingsetting = new AdminSystemSetting();
				adminsettingsetting.show();
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
