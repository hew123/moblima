package files;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import allData.DataBooking;
import allData.DataCineplex;
import allData.DataMovie;
import allData.DataPrice;
import allData.DataPublicholiday;
import allData.DataShowtime;
import allframe.Booking;
import allframe.Cineplex;
import allframe.Movie;
import allframe.Price;
import allframe.Publicholiday;
import allframe.Seat;
import allframe.Showtime;

public class SLmovie {

	Scanner sc = new Scanner(System.in);
	
	public void show() {
		
		int choice = 0;
		
		do {
			System.out.println("1) Search (Now Showing) movie(s) by keyword(s)");
			System.out.println("2) List all (Now Showing) movies ");
			System.out.println("3) Back");
			System.out.print("Select choice: ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				Movie i = searchMovie();
				if (i != null) {
					CURbooking curbooking = new CURbooking(); //if there is problem here use static
					curbooking.booking(i);
				}
				break;
				
			case 2:
				Movie j = listMovie();
				if (j != null) {
					CURbooking curbooking = new CURbooking(); //if there is problem here use static
					curbooking.booking(j);
				}
				break;
				
			}
		}while(choice !=3);
	}
	
	
	private DataMovie datamovie = DataMovie.getInstance();

	
	public Movie searchMovie() {
		String word;
		System.out.print("Enter your search word(s): ");
		sc.nextLine();
		word = sc.nextLine();
		List<Movie> tmpmovie = new ArrayList<Movie>();
		for(int i = 0; i<datamovie.getMovies().size(); i++) {
			if(datamovie.getMovies().get(i).getTitle().toLowerCase().contains(word.toLowerCase())){
				tmpmovie.add(datamovie.getMovies().get(i));
			}
		}
		if (tmpmovie.size() == 0) {
			System.out.println("Keyword(s) did not match any movies!");
			return null;
		}
		else {
			for(Movie m: tmpmovie) {
				if(m.getShowStatus().equals("Now Showing")) {
				System.out.println(m.getIndex() + ") " + m.getTitle() + " (" + m.getGuidance() + ") (" + m.getShowStatus() + ")");
				}
			}
			System.out.print("Select the movie to book (enter -1 to go back): ");
			int selmovie = sc.nextInt();
			System.out.println();
			if(selmovie == -1) {
				return null;
			}
			Movie choicebook = datamovie.getMovies().get(selmovie-1);
			return choicebook;
		}
	}
	
	
	public Movie listMovie() {
		for(Movie m: datamovie.getMovies()) {
			if(m.getShowStatus().equals("Now Showing")) {
				System.out.println(m.getIndex() + ") " + m.getTitle() + " (" + m.getGuidance() + ") (" + m.getShowStatus() + ")");
			}
		}
		System.out.print("Select the movie to book (enter -1 to go back): ");
		int selmovie = sc.nextInt();
		if(selmovie == -1) {
			return null;
		}
		Movie choicebook = datamovie.getMovies().get(selmovie-1);
		return choicebook;
	}
	
}
