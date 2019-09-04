package files;

import java.util.Scanner;

import allData.DataMovie;
import allframe.Movie;

//index refers to movie. eg first movie = index 1, altho array is still 0.
public class CURmovies {
	
	public void show(){
		Scanner sc = new Scanner(System.in);
		int choice=0;
		
		while(choice!=4){
			System.out.println("1) Create Movie");
			System.out.println("2) Update Movie");
			System.out.println("3) Remove Movie");
			System.out.println("4) Back");
			System.out.print("Please enter your choice : ");
			choice = sc.nextInt();				
			switch(choice){
				case 1:
					askCreateMovie();
					break;
					
				case 2:
					askUpdateMovie();
					break;
					
				case 3:
					askRemoveMovie();
					break;
					
				case 4:
					return;
			}
		}
	}
	
	private DataMovie datamovie = DataMovie.getInstance();
	
	public void askCreateMovie() {
		int choice;
		int index = datamovie.getMovies().size()+1;
		Scanner sc = new Scanner(System.in);
		String title, guidance = "G", type = "Digital", showingstatus = "Now Showing", synopsis, director, cast;

		System.out.println();
		System.out.print("Enter the Movie Title: ");
		title = sc.nextLine();
		System.out.println();
		
		
		System.out.println("Guidance");
		System.out.println("------------");
		System.out.println("1) G");
		System.out.println("2) PG");
		System.out.println("3) PG13");
		System.out.println("4) NC16");
		System.out.println("5) M18");
		System.out.println("6) R21");
		System.out.print("Choose the Movie Guidance: ");
		choice = sc.nextInt();
		if(choice>6 || choice<1){
			System.out.println("Invalid Choice!");
			return;
		}
		System.out.println();
		switch(choice){
			case 1:
				guidance = "G";
				break;
			case 2:
				guidance = "PG";
				break;
			case 3:
				guidance = "PG13";
				break;
			case 4:
				guidance = "NC16";
				break;
			case 5:
				guidance = "M18";
				break;
			case 6:
				guidance = "R21";
				break;
		}
		
		
		System.out.println("Movie Type");
		System.out.println("----------");
		System.out.println("1) Digital");
		System.out.println("2) 3D");
		System.out.print("Choose the Movie Type: ");
		choice = sc.nextInt();
		if(choice>2 || choice<1){
			System.out.println("Error invalid choice!");
			return;
		}
		System.out.println();
		switch(choice){
			case 1:
				type = "Digital";
				break;
				
			case 2:
				type = "3D";
				break;
		}
		
		
		System.out.println("Movie Showing Status");
		System.out.println("--------------------");
		System.out.println("1) COMING SOON");
		System.out.println("2) NOW SHOWING");
		System.out.println("3) END OF SHOWING");
		System.out.print("Choose the Movie Showing Status: ");
		choice = sc.nextInt();
		if(choice>3 || choice<1){
			System.out.println("Invalid Choice! Restart!!");
			System.out.println();
			return;
		}
		System.out.println();
		switch(choice){
			case 1:
				showingstatus = "Coming Soon";
				break;
			case 2:
				showingstatus = "Now Showing";
				break;
			case 3:
				showingstatus = "End Of Showing";
				break;
		}
		
		sc.nextLine();
		System.out.print("Enter the Movie Synopsis: ");
		synopsis = sc.nextLine();
		System.out.println();
		
		
		System.out.print("Enter the Movie Director: ");
		director = sc.nextLine();
		System.out.println();
		
		
		System.out.print("Enter the Movie Casts (seperate each person by comma): ");
		cast = sc.nextLine();
		System.out.println();

		
		System.out.println("Please check:");
		System.out.println("Title: "+title);
		System.out.println("Guidance: "+ guidance);
		System.out.println("Type: "+ type);
		System.out.println("Showing Status: "+ showingstatus);
		System.out.println("Synopsis: "+ synopsis);
		System.out.println("Director: "+ director);
		System.out.println("Cast: "+ cast);
		System.out.print("Enter Y/y to confirm. N/n to cancel. Confirm: ");
		String yn = sc.next();
		if (yn.equals("Y") || yn.equals("y")) {
			Movie movie = new Movie(index,title,guidance,type,showingstatus,synopsis,director,cast);
			datamovie.createMovie(movie);
		}
		else {
			System.out.println();
			return;
		}
		System.out.println("Success! Movie created!");
		System.out.println();
	}
	
	
	public void askUpdateMovie() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Here are the list of movies:");
		System.out.println("----------------------------");
		for (int i=0; i < datamovie.getMovies().size(); i++) {
			System.out.println(datamovie.getMovies().get(i).getIndex() + ") " +  datamovie.getMovies().get(i).getTitle() + " (" + datamovie.getMovies().get(i).getShowStatus() + ")" );
		}

		System.out.println();
		System.out.print("Select the movie to update: ");
		int choice = sc.nextInt();
		System.out.println();
		int index = datamovie.getMovies().get(choice-1).getIndex();
		String title = datamovie.getMovies().get(choice-1).getTitle();
		String guidance = datamovie.getMovies().get(choice-1).getGuidance();
		String type = datamovie.getMovies().get(choice-1).getType();
		String showingstatus = datamovie.getMovies().get(choice-1).getShowStatus();
		String synopsis = datamovie.getMovies().get(choice-1).getSynopsis();
		String director = datamovie.getMovies().get(choice-1).getDirector();
		String cast = datamovie.getMovies().get(choice-1).getCasts();
		int choice2 = 0;
		do {
			System.out.println("Update Movie:");
			System.out.println("-------------");
			System.out.println("1) Title: " + title);
			System.out.println("2) Guidance: " + guidance);
			System.out.println("3) Type: " + type);
			System.out.println("4) Showing Status: "  + showingstatus);
			System.out.println("5) Synopsis: " + synopsis);
			System.out.println("6) Director: "+ director);
			System.out.println("7) Casts: "+ cast);
			System.out.println("8) Back and Save");
			System.out.println("");
			System.out.print("Choose the attribute for update: ");
			choice2 = sc.nextInt();
			sc.nextLine();
			if(choice2>8 || choice2<1){
				System.out.println("Invalid Choice!");
				return;
			}

			switch(choice2) {
			case 1:
				System.out.print("Update Title: ");
				title = sc.nextLine();
				break;
			case 2:
				System.out.println("Update Guidance : ");
				System.out.println("1) G");
				System.out.println("2) PG");
				System.out.println("3) PG13");
				System.out.println("4) NC16");
				System.out.println("5) M18");
				System.out.println("6) R21");
				System.out.print("Choose the Movie Guidance: ");
				int choice3 = sc.nextInt();
				if(choice3>6 || choice3<1){
					System.out.println("Invalid Choice!");
					return;
				}
				System.out.println();
				switch(choice3){
					case 1:
						guidance = "G";
						break;
					case 2:
						guidance = "PG";
						break;
					case 3:
						guidance = "PG13";
						break;
					case 4:
						guidance = "NC16";
						break;
					case 5:
						guidance = "M18";
						break;
					case 6:
						guidance = "R21";
						break;
				}
				break;
			case 3:
				System.out.println("Update Type: ");
				System.out.println("Movie Type");
				System.out.println("----------");
				System.out.println("1) Digital");
				System.out.println("2) 3D");
				System.out.print("Choose the Movie Type: ");
				int choice4 = sc.nextInt();
				if(choice4>2 || choice4<1){
					System.out.println("Error invalid choice!");
					return;
				}
				System.out.println();
				switch(choice4){
					case 1:
						type = "Digital";
						break;
						
					case 2:
						type = "3D";
						break;
				}
				break;
			case 4:
				System.out.println("Update Showing Status: ");
				System.out.println("Movie Showing Status");
				System.out.println("--------------------");
				System.out.println("1) COMING SOON");
				System.out.println("2) NOW SHOWING");
				System.out.println("3) END OF SHOWING");
				System.out.print("Choose the Movie Showing Status: ");
				choice = sc.nextInt();
				if(choice>3 || choice<1){
					System.out.println("Invalid Choice! Restart!!");
					System.out.println();
					return;
				}
				System.out.println();
				switch(choice){
					case 1:
						showingstatus = "Coming Soon";
						break;
					case 2:
						showingstatus = "Now Showing";
						break;
					case 3:
						showingstatus = "End Of Showing";
						break;
				}
				break;
			case 5:
				System.out.print("Update Synopsis: ");
				synopsis = sc.nextLine();
				break;
			case 6:
				System.out.print("Update Director: ");
				director = sc.nextLine();
				break;
			case 7:
				System.out.print("Update Casts: ");
				cast = sc.nextLine();
				break;
			case 8: break;
			default:
				System.out.println("Invalid option! Choose again! ");
			}

		}while(choice2<8 && choice2>0);
		
			System.out.println("Updated Movie:");
			System.out.println("-------------");
			System.out.println("Title: " + title);
			System.out.println("Guidance: " + guidance);
			System.out.println("Type: " + type);
			System.out.println("Showing Status: "  + showingstatus);
			System.out.println("Synopsis: " + synopsis);
			System.out.println("Director: "+ director);
			System.out.println("Casts: "+ cast);
			System.out.println();
			Movie movie = new Movie(index, title, guidance, type, showingstatus, synopsis, director, cast);
			datamovie.updateMovie(movie);
			System.out.println("Update success!");
			System.out.println();
	}
	
	
	
	public void askRemoveMovie() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("Movies that are Now Showing:");
		System.out.println("----------------------------");
		for (int i=0; i<datamovie.getMovies().size(); i++) {
			if (datamovie.getMovies().get(i).getShowStatus().equals("Now Showing")) {
				System.out.println((datamovie.getMovies().get(i).getIndex()) + ") " + datamovie.getMovies().get(i).getTitle());
			}
		}
		System.out.print("Which movie to remove? (Set to End Of Showing) : ");
		int choice = sc.nextInt();
		datamovie.removeMovie(choice-1);
		System.out.println("Suscessfully removed.");
		System.out.println();
	}
}
