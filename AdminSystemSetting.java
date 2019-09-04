package files;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import allData.DataPrice;
import allData.DataPublicholiday;
import allframe.Publicholiday;

public class AdminSystemSetting {

	Scanner sc = new Scanner(System.in);
	
	public void show() {
		int choice;
		
		do {
		System.out.println("System Settings");
		System.out.println("---------------");
		System.out.println("1) Set Prices");
		System.out.println("2) Set Holidays");
		System.out.println("3) Back");
		System.out.print("Select an option: ");
		choice = sc.nextInt();
		System.out.println();
		switch(choice) {
			case 1:
				CURprice curprice = new CURprice();
				curprice.setPrice();
				break;
			case 2: 
				CURpublicholiday curpublicholiday = new CURpublicholiday();
				curpublicholiday.printPH();
				curpublicholiday.setHoliday();
				break;
			}
		}while(choice !=3);
	}
}
