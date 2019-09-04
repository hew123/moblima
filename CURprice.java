package files;

import java.util.Scanner;

import allData.DataPrice;

public class CURprice {
	
	private DataPrice dataprice = DataPrice.getInstance();
	Scanner sc = new Scanner(System.in);
	
	public void setPrice() {
		System.out.println("Pricing of current ticket types");
		System.out.println("----------------------");
		System.out.printf("1) Senior Price (Digital) (Mon-Thu): $%.2f \n", dataprice.getPrices().getmon_thu_senior_price_d());
		System.out.printf("2) Student Price (Digital) (Mon-Thu): $%.2f \n", dataprice.getPrices().getmon_thu_student_price_d());
		System.out.printf("3) Student Price (3D) (Mon-Thu): $%.2f \n", dataprice.getPrices().getmon_thu_student_price_3d());
		System.out.printf("4) Platinum Price (All Day): $%.2f \n", dataprice.getPrices().getplatinum_price());
		System.out.printf("5) Normal Price (Digital) (Mon-Thu): $%.2f \n", dataprice.getPrices().getmon_thu_price_d());
		System.out.printf("6) Normal Price (3D) (Mon-Thu): $%.2f \n", dataprice.getPrices().getmon_thu_price_3d());
		System.out.printf("7) Normal Price (Digital) (Fri-Sun): $%.2f \n", dataprice.getPrices().getfri_sun_PH_price_d());
		System.out.printf("8) Normal Price (3D) (Fri-Sun): $%.2f \n", dataprice.getPrices().getfri_sun_PH_price_3d());
		System.out.println();
		int c;
		do {
			System.out.print("Which pricing to change? (-1 to go back) : ");
			c = sc.nextInt();
			float newprice;
			switch(c) {
			case 1:
				System.out.print("Enter new price: $");
				newprice = sc.nextFloat();
				dataprice.getPrices().setmon_thu_senior_price_d(newprice);
				break;
			case 2:
				System.out.print("Enter new price: $");
				newprice = sc.nextFloat();
				dataprice.getPrices().setmon_thu_student_price_d(newprice);
				break;
			case 3:
				System.out.print("Enter new price: $");
				newprice = sc.nextFloat();
				dataprice.getPrices().setmon_thu_student_price_3d(newprice);
				break;
			case 4:
				System.out.print("Enter new price: $");
				newprice = sc.nextFloat();
				dataprice.getPrices().setall_platinum_price(newprice);
				break;
			case 5:
				System.out.print("Enter new price: $");
				newprice = sc.nextFloat();
				dataprice.getPrices().setmon_thu_price_d(newprice);
				break;
			case 6:
				System.out.print("Enter new price: $");
				newprice = sc.nextFloat();
				dataprice.getPrices().setmon_thu_price_3d(newprice);
				break;
			case 7:		
				System.out.print("Enter new price: $");
				newprice = sc.nextFloat();	
				dataprice.getPrices().setfri_sun_PH_price_d(newprice);
				break;
			case 8:
				System.out.print("Enter new price: $");
				newprice = sc.nextFloat();
				dataprice.getPrices().setfri_sun_PH_price_3d(newprice);
				break;
			}
			if(c==1 ||c==2 ||c==3 ||c==4 ||c==5 ||c==6 ||c==7 ||c==8) {
				dataprice.savePrices();
				System.out.println("Saved Successfully!");
				System.out.println();
			}
		}while(c != -1);
		
	}
	
}
