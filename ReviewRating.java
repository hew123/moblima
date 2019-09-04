package files;

import java.util.List;
import java.util.Scanner;

import allData.DataMovie;
import allframe.Movie;

public class ReviewRating {

	private DataMovie datamovie = DataMovie.getInstance();
	private List<Movie> movies = datamovie.getMovies();
	
	Scanner sc = new Scanner(System.in);
	
	public void show() {
		
		int choice;
		do {
		System.out.println();
		System.out.println("Review and Rating");
		System.out.println("-----------------");
		System.out.println("1) Give review and rating");
		System.out.println("2) View review and rating");
		System.out.println("3) View overall(average) rating for each movie");
		System.out.println("4) Back");
		System.out.print("Please enter choice: ");
		choice = sc.nextInt();
		switch(choice) {
		case 1:
			give();
			break;
		case 2:
			view();
			break;
		case 3:
			overallrating();
		}
		}while(choice != 4);
	}
	
	public void give() {
		for(Movie m: movies) {
			if(m.getShowStatus().equals("Now Showing") || m.getShowStatus().equals("End Of Showing")) {
				System.out.println(m.getIndex() + ") " + m.getTitle() + " (" + m.getGuidance() + ") " + " (" + m.getShowStatus() + ")");
			}
		}
		System.out.print("Select a movie to review and rate: ");
		int selmovie = sc.nextInt();
		System.out.print("Your Review: ");
		sc.nextLine();
		String rev = sc.nextLine();
		System.out.println();
		System.out.print("Your Rating (0-5): ");
		float rat;
		rat = sc.nextFloat();
		while(rat<0 || rat>5) {
			System.out.println("only 0 - 5 !");
			System.out.print("Enter rating again: ");
			rat = sc.nextFloat();
		}
		System.out.println();
		datamovie.updateRR(selmovie, rev, rat);
		System.out.println("Review and rating submitted!");
	}
	
	public void view() {
		for(Movie m: movies) {
			if(m.getShowStatus().equals("Now Showing") || m.getShowStatus().equals("End Of Showing")) {
				System.out.println(m.getIndex() + ") " + m.getTitle() + " (" + m.getGuidance() + ") " + " (" + m.getShowStatus() + ")");
			}
		}
		System.out.print("Select a movie to view review and ratings: ");
		int selmovie = sc.nextInt();
		int count = 0;
		System.out.println();
		if(movies.get(selmovie-1).getReviews() == null) {
			System.out.println("No rating/ review has been given yet.");
			return;
		}
		for(String r: movies.get(selmovie-1).getReviews()) {
			System.out.println("+(Rating: " + movies.get(selmovie-1).getRatings().get(count) + ") "  + r) ;
			count++;
		}
	}
	
	public void overallrating() {
		for(Movie m: movies) {
			if(m.getShowStatus().equals("Now Showing") || m.getShowStatus().equals("End Of Showing")) {
				System.out.print(m.getTitle() + " (" + m.getGuidance() + ") " + " (" + m.getShowStatus() + ")");
				float totalrating = 0;
				int countrating = 0;
				int whichmovieindex = movies.indexOf(m);
				if(movies.get(whichmovieindex).getRatings() == null) {
					System.out.println(" (Average Rating: N.A.)");
					
				}
				else {
					for(float rating : movies.get(whichmovieindex).getRatings()) {
						totalrating += rating;
						countrating++;
					}
					float average = (float)totalrating/(float)countrating;
					System.out.println(" (Average Rating: " + average + ")");
				}
			}
		}
	}

}
