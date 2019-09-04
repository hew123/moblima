package files;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import allData.DataBooking;
import allframe.Booking;

public class Bookinghistory {
	
	Scanner sc = new Scanner(System.in);
	
	public void show() {
		int choice;
		do {
		System.out.println("1) Search booking history by Phone no.");
		System.out.println("2) Search booking history by Email");
		System.out.println("3) Back");
		System.out.print("Please enter choice: ");
		choice = sc.nextInt();
		switch(choice) {
			case 1:
				byPhone();
				break;
			case 2:
				byEmail();
				break;
			}
		}while(choice != 3);
	}
	
	DataBooking databooking = DataBooking.getInstance();
	
	public void byPhone() {
		System.out.print("Enter phone no.: ");
		String phone = sc.next();
		System.out.println();
		System.out.println("All booking history from the phone no. provided");
		System.out.println("-----------------------------------------------");
		int count = 0;
		for(Booking b: databooking.getBookings()) {
			if (b.getPhone().equals(phone)) {
				printOneBooking(b);
				System.out.println();
				count++;
			}
		}
		if(count == 0) {
			System.out.println("There is no record for this phone no.");
			System.out.println();
			return;
		}
	}
	
	public void byEmail() {
		System.out.print("Enter Email: ");
		String email = sc.next();
		System.out.println();
		System.out.println("All booking history from the email provided");
		System.out.println("--------------------------------------------");
		int count = 0;
		for(Booking b: databooking.getBookings()) {
			if (b.getEmail().equals(email)) {
				printOneBooking(b);
				System.out.println();
				count++;
			}
		}
		if(count == 0) {
			System.out.println("There is no record for this email.");
			System.out.println();
			return;
		}
	}
	

	public void printOneBooking(Booking b) {
		String tid = b.getTid();
		String name = b.getName();
		float amt = b.getAmount();
		String dateofbooking = new SimpleDateFormat("d/M/y HH:mm").format(b.getDateOfBooking());
		String cinemaname = b.getShowtime().getCinema().getCinemaName();
		String cinemaclass = b.getShowtime().getCinema().getCinemaClass();
		String movietitle = b.getShowtime().getMovie().getTitle();
		String movietype = b.getShowtime().getMovie().getType();
		String movieguidance = b.getShowtime().getMovie().getGuidance();
		String showdatetime = new SimpleDateFormat("d/M/y HH:mm").format(b.getShowtime().getDate());
		String seats= b.getSeats();
		System.out.println("Transaction ID:   " + tid);
		System.out.println("Transaction Time: " + dateofbooking);
		System.out.println("Name:             " + name);
		System.out.printf("Total Paid:       $%.2f \n", amt);
		System.out.println("Movie Title:      " + movietitle + " (" + movieguidance + ") " + "(" + movietype + ")");
		System.out.println("Cinema:           " + cinemaname + " (" + cinemaclass + ")");
		System.out.println("Show Time:        " + showdatetime);
		System.out.println("Seat(s) booked:   " + seats);
	}
}
