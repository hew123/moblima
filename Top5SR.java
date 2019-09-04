package files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import allData.DataBooking;
import allData.DataMovie;
import allframe.Booking;
import allframe.Movie;

public class Top5SR {

	Scanner sc = new Scanner(System.in);
	
	public void show() {
		int choice;
		do {
			System.out.println("1) Top 5 movies by Sales");
			System.out.println("2) Top 5 movies by Ratings");
			System.out.println("3) Back");
			System.out.print("Please enter choice: ");
			choice = sc.nextInt();
			System.out.println();
			switch(choice) {
			case 1:
				bySales();
				break;
			case 2:
				byRatings();
				break;
			}
		}while(choice != 3);
	}
	
	DataBooking databooking = DataBooking.getInstance();
	DataMovie datamovie = DataMovie.getInstance();
	List<Movie> movies = datamovie.getMovies();
	
	public void bySales() {
		System.out.println("Top 5 movies by Sales");
		System.out.println("---------------------");
		HashMap<Movie, Float> hm = new HashMap<Movie, Float>();
		List<String> sortedhm = new ArrayList<String>();
		for(Booking b: databooking.getBookings()) {
			float thisBookingAmount = b.getAmount();
			Movie movie = b.getShowtime().getMovie();
			if(hm.containsKey(movie)){
				float currentTotalAmount = hm.get(movie);
				currentTotalAmount += thisBookingAmount;
				//hm.remove(movie);
				hm.put(movie, currentTotalAmount);
			}
			else {
				hm.put(movie, thisBookingAmount);
			}
		}
		for(int i=0; i<5; i++) {
			float largestsale = 0;
			Movie largestmovie = null;
			for (Map.Entry<Movie, Float> entry : hm.entrySet()) {
				if(entry.getValue() > largestsale) {
					largestsale = entry.getValue();
					largestmovie = entry.getKey();
				}
			}
			if(largestmovie != null) {
				hm.remove(largestmovie);
				sortedhm.add(largestmovie.getTitle() + " (" + largestmovie.getShowStatus() + ")" + " -- $" + String.format("%.2f", largestsale));
			}
		}
		int count = 0;
		for (String str: sortedhm) {
			count++;
			System.out.println(str);
		}
		if (count == 0) {
			System.out.println("No movie made any sales yet!");
		}
		System.out.println();
	}
	
	public void byRatings() {
		System.out.println("Top 5 movies by Ratings");
		System.out.println("-----------------------");
		HashMap<Movie, Float> hm = new HashMap<Movie, Float>();
		List<String> sortedhm = new ArrayList<String>();
		for(Movie m: movies) {
			if(m.getShowStatus().equals("Now Showing") || m.getShowStatus().equals("End Of Showing")) {
				float average = -1;
				float totalrating = 0;
				int countrating = 0;
				int whichmovieindex = movies.indexOf(m);
				if(movies.get(whichmovieindex).getRatings() == null) {
					average = -1;
					
				}
				else {
					for(float rating : movies.get(whichmovieindex).getRatings()) {
						totalrating += rating;
						countrating++;
					}
					average = (float)totalrating/(float)countrating;
				}
				hm.put(m, average);
			}
		}
		for(int i=0; i<5; i++) {
			float highestrate = -1;
			Movie highestmovie = null;
			for (Map.Entry<Movie, Float> entry : hm.entrySet()) {
				if(entry.getValue() > highestrate) {
					highestrate = entry.getValue();
					highestmovie = entry.getKey();
				}
			}
			if(highestmovie != null) {
				hm.remove(highestmovie);
				sortedhm.add(highestmovie.getTitle() + " (" + highestmovie.getShowStatus() + ")" + " -- Avg.Rating: " + highestrate);
			}
		}
		int count = 0;
		for (String str: sortedhm) {
			count++;
			System.out.println(str);
		}
		if (count == 0) {
			System.out.println("No movie have rating yet!");
		}

		System.out.println();
	}
}
