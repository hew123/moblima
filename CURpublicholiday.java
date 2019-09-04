package files;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import allData.DataPublicholiday;
import allframe.Publicholiday;

public class CURpublicholiday {
	
	private DataPublicholiday datapublicholiday = DataPublicholiday.getInstance();
	Scanner sc = new Scanner(System.in);
	
	public void printPH() {
		System.out.println("Public Holidays");
		System.out.println("---------------");
		if(datapublicholiday.getPublicHolidays().size() == 0) { //.null does'nt work
			System.out.println("No public holiday is set yet.");
		}
		else {
			int count = 1;
			for(Publicholiday ph: datapublicholiday.getPublicHolidays()) {
				System.out.println(count + ") " + ph.getName() + " " + new SimpleDateFormat("d/M").format(ph.getDate()) + " (dd/mm)");
				count++;
			}
		}
		System.out.println();
	}
	
	
	public void setHoliday() {
		
		int choice;
		do {
			System.out.println("1) Create a new holiday");
			System.out.println("2) Remove an existing holiday");
			System.out.println("3) Back");
			System.out.print("Please enter choice: ");
			choice = sc.nextInt();
			System.out.println();
			
			switch(choice){
				case 1:
					System.out.print("Enter a name for the new Public Holiday: ");
					sc.nextLine();
					String name = sc.nextLine();
					System.out.println();
					System.out.print("Enter the date (dd/mm): ");
					String date = sc.nextLine();
					try{
						Publicholiday newph = new Publicholiday(name, new SimpleDateFormat("d/M").parse(date));
						datapublicholiday.createPH(newph);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("Created Successfully!");
					System.out.println();
					break;
				case 2:
					//printPH();
					System.out.print("Select the holiday to remove: (-1 to go back)");
					int rph = sc.nextInt();
					if (rph == -1)return;
					Publicholiday removeph = datapublicholiday.getPublicHolidays().get(rph-1);
					datapublicholiday.removePH(removeph);
					System.out.println("Removed Successfully!");
					System.out.println();
					break;
			}
		}while(choice != 3);
	}
}
